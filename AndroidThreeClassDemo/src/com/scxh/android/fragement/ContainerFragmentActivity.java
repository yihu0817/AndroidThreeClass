package com.scxh.android.fragement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.fragement.MyListFragment.ShowDialogListFragmentLisner;

public class ContainerFragmentActivity extends Activity implements
		ShowDialogListFragmentLisner {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_container_fragment_layout);
		getFragmentManager().beginTransaction()
				.add(R.id.contanerFragments, new MyListFragment()).commit();
	}

	public static class MyAlertDialogFragment extends DialogFragment {

		public static MyAlertDialogFragment newInstance(int postion,
				String title) {
			MyAlertDialogFragment frag = new MyAlertDialogFragment();
			Bundle args = new Bundle();
			args.putString("title", title);
			args.putInt("POSTION", postion);
			frag.setArguments(args);
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			String title = getArguments().getString("title");
			final int position = getArguments().getInt("POSTION");
			return new AlertDialog.Builder(getActivity())
					.setIcon(R.drawable.ic_launcher)
					.setTitle(title)
					.setPositiveButton("ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
//									Toast.makeText(getActivity(),"Positive click!",Toast.LENGTH_SHORT).show();

									getFragmentManager()
											.beginTransaction()
											.replace(R.id.contanerFragments,ArticleFragment.newInstance(position))
											.addToBackStack(null)
											.commit();
								}
							})
					.setNegativeButton("cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Toast.makeText(getActivity(),"Negative click!",Toast.LENGTH_SHORT).show();
								}
							}).create();
		}
	}

	@Override
	public void onShow(int postion, String item) {
		DialogFragment newFragment = MyAlertDialogFragment.newInstance(postion,
				item);
		newFragment.show(getFragmentManager(), "dialog");
	}
}
