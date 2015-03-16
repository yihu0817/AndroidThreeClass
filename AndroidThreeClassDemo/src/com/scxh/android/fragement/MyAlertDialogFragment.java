package com.scxh.android.fragement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class MyAlertDialogFragment extends DialogFragment {
	String mTitle;
	public OnAlertDialogFragmentListener mCallback;
	
	public interface OnAlertDialogFragmentListener {
		public void onPositiveClick();
		public void onNegativeClick();
	}
	
	public static MyAlertDialogFragment newInstance(String title) {
		MyAlertDialogFragment frag = new MyAlertDialogFragment();
		
		Bundle args = new Bundle();
		args.putString("title", title);
		frag.setArguments(args);
		
		return frag;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallback = (OnAlertDialogFragmentListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTitle = getArguments().getString("title");
	}
	//法二
//	 @Override
//     public View onCreateView(LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState) {
//		
//        View v = inflater.inflate(R.layout.article_view, container, false);
//        View tv = v.findViewById(R.id.article);
//        ((TextView)tv).setText(mTitle);
//        return v;
//    }
	  
	  
		//法一
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
	
			return new AlertDialog.Builder(getActivity())
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(mTitle)
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									mCallback.onPositiveClick();
								}
							})
					.setNegativeButton(android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									mCallback.onNegativeClick();
								}
							}).create();
		}
}