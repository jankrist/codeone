package cz.jankrist.codeone.view.fragment.navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import cz.jankrist.codeone.R;
import cz.jankrist.codeone.view.activity.CodeDetailActivity;
import timber.log.Timber;

/**
 * Created by jankristdev@gmail.com on 07.03.2017.
 */

public class CodeReaderFragment extends NavigationTabFragment implements SurfaceHolder.Callback {

    private static final int RC_PERMISSION_CAMERA = 101;

    private BarcodeDetector detector;
    private SurfaceView barcodeReaderSurface;
    private CameraSource cameraSource;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_reader, container, false);
        barcodeReaderSurface = (SurfaceView) rootView.findViewById(R.id.surface_view);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(checkCameraPermission()){
            setupBarcodeReader();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        detector.release();
    }

    @Override
    protected int getTitle() {
        return R.string.title_tab_reader;
    }

    private void setupBarcodeReader(){
        detector = new BarcodeDetector.Builder(getContext().getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                // nothing else to release
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                SparseArray<Barcode> detectedItems = detections.getDetectedItems();
                if(detectedItems.size() > 0){
                    detector.release();
                    Barcode barcode = detectedItems.valueAt(0);
                    Intent intent = new Intent(getActivity(), CodeDetailActivity.class);
                    intent.putExtra(CodeDetailActivity.EXTRA_BARCODE, barcode);
                    startActivity(intent);

                }

            }
        });


        cameraSource = new CameraSource.Builder(getContext(), detector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();

        barcodeReaderSurface.getHolder().addCallback(this);

        if(!detector.isOperational()){
            Toast.makeText(getContext(), "Could not set up the detector!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkCameraPermission(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Timber.d("Permissions for camera requested.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RC_PERMISSION_CAMERA);
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            cameraSource.start(barcodeReaderSurface.getHolder());
        } catch (IOException e) {
            Timber.e("Camera source I/O exception: %s", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // not needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // not needed
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode ==  RC_PERMISSION_CAMERA){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay!
                setupBarcodeReader();

            } else {
                Timber.d("Fuck you!");
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }

    }
}
