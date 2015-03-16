package com.scxh.android.ui.dialog;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.scxh.android.R;

public class DialogAcitivity extends Activity {
	private static final int ALERT_DIALOG = 0;
	private static final int SINGLE_DIALOG = 1;
	private static final int MULTICHOICE_DIALOG = 2;
	private static final int MYCUSTOME_DIALOG = 3;
	private static final int DATETIME_DIALOG = 4;
	private static final int WEIXINH_DIALOG = 5;
	private Button mAlertDialogoneBtn, mSingleDialog, mMultiChoiceDialog,
			mMycustomDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dailog_layout);

		mAlertDialogoneBtn = (Button) findViewById(R.id.alertDialog1);
		mSingleDialog = (Button) findViewById(R.id.singleDialog);
		mMultiChoiceDialog = (Button) findViewById(R.id.multiChoiceDialog);
		mMycustomDialog = (Button) findViewById(R.id.mycustomDialog);
		mAlertDialogoneBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(ALERT_DIALOG);
			}
		});

		mSingleDialog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(SINGLE_DIALOG);
			}
		});

		mMultiChoiceDialog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(MULTICHOICE_DIALOG);
			}
		});

		mMycustomDialog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(MYCUSTOME_DIALOG);

			}
		});
	}

	public void onMyCustomeDialogTwoClick(View v) {
		showDialog(DATETIME_DIALOG);
	}

	public void onWeixinDialogClick(View v) {
		showDialog(WEIXINH_DIALOG);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ALERT_DIALOG:
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("操作提示");
			builder.setMessage("是否退出？");

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogAcitivity.this, "取消",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							LayoutInflater inflater = LayoutInflater.from(DialogAcitivity.this);
							View v = inflater.inflate(R.layout.dialog_mycustom_layout, null);
							
							Toast toast = Toast.makeText(DialogAcitivity.this,
									"确定", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 100);
							
							
							toast.setView(v);
							toast.show();
						}
					});

			builder.setSingleChoiceItems(
					getResources().getStringArray(R.array.fun_dialog), 3,
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogAcitivity.this,
									"which " + which, Toast.LENGTH_SHORT)
									.show();
						}
					});

			AlertDialog dialog = builder.create();
			return dialog;

		case SINGLE_DIALOG:
			String[] array = getResources().getStringArray(R.array.fun_dialog);
			OnClickListener onClickListener = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(DialogAcitivity.this, "which " + which,
							Toast.LENGTH_SHORT).show();
				}
			};
			int itemSelct = 3; // 默认选中项

			AlertDialog.Builder builder1 = new Builder(this);
			builder1.setTitle("操作提示");

			builder1.setSingleChoiceItems(array, itemSelct, onClickListener);
			builder1.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogAcitivity.this, "确定",
									Toast.LENGTH_SHORT).show();
						}
					});
			AlertDialog dialog1 = builder1.create();
			return dialog1;
		case MULTICHOICE_DIALOG:
			String[] arrays = new String[] { "电影", "读书", "音乐", "打游戏" };
			boolean[] choicks = new boolean[] { false, false, false, true };

			OnMultiChoiceClickListener onClickListeners = new OnMultiChoiceClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which,
						boolean isChecked) {
					Toast.makeText(DialogAcitivity.this,
							"确定isChecked :" + isChecked, Toast.LENGTH_SHORT)
							.show();
				}
			};

			AlertDialog.Builder builder2 = new Builder(this);
			builder2.setTitle("操作提示");

			builder2.setMultiChoiceItems(arrays, choicks, onClickListeners);

			builder2.setPositiveButton(getString(R.string.confirm_btn),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogAcitivity.this,
									getString(R.string.confirm_btn),
									Toast.LENGTH_SHORT).show();
						}
					});
			AlertDialog dialog2 = builder2.create();
			return dialog2;

		case MYCUSTOME_DIALOG:
			LayoutInflater inflater = LayoutInflater.from(DialogAcitivity.this);
			View v = inflater.inflate(R.layout.dialog_mycustom_layout, null);
			Button b = (Button) v.findViewById(R.id.confirmbtn);
			b.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(DialogAcitivity.this,
							getString(R.string.confirm_btn), Toast.LENGTH_SHORT)
							.show();
					dismissDialog(MYCUSTOME_DIALOG);
				}
			});
			AlertDialog.Builder builder3 = new Builder(this);
			builder3.setView(v);
			AlertDialog dialog3 = builder3.create();
			return dialog3;
		case DATETIME_DIALOG:
			// DatePickerDialog datePickerDialog = new DatePickerDialog();

			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			TimePickerDialog timePickerDialog = new TimePickerDialog(this,
					new OnTimeSetListener() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							Toast.makeText(
									DialogAcitivity.this,
									"hourOfDay  :" + hourOfDay + "minute :"
											+ minute, Toast.LENGTH_SHORT)
									.show();

						}
					}, hour, minute, DateFormat.is24HourFormat(this));

			return timePickerDialog;
		case WEIXINH_DIALOG:
			MyWeixinDialog weixinDialog = new MyWeixinDialog(this);
			return weixinDialog;
		}
		return null;
	}
}
