package io.github.vhoyer.timecrashstats;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by vhoyer on 01/05/17.
 */

public class SyncFragment extends Fragment {

	public void scanQR(){
		IntentIntegrator integrator = new FragmentIntentIntegrator(this);

		integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
		integrator.setPrompt("Scan a QR code");
		integrator.setCameraId(0);  // Use a specific camera of the device
		integrator.setBeepEnabled(false);
		integrator.setBarcodeImageEnabled(true);
		integrator.setOrientationLocked(false);

		integrator.initiateScan();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

		if(result != null) {
			if(result.getContents() == null) {
				new AlertDialog.Builder(this.getActivity())
					.setTitle("not Scanned")
					.setMessage("There was an error")
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					})
				.show();
			} else {
				new AlertDialog.Builder(this.getActivity())
					.setTitle("Scanned")
					.setMessage(result.getContents())
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					})
				.show();
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
}
