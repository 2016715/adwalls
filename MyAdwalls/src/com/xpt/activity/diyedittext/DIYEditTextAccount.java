package com.xpt.activity.diyedittext;

import com.example.adwalls.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class DIYEditTextAccount extends EditText implements TextWatcher {

	/**
	 * λ�ڿؼ������EditText���ݵ�ͼƬ��Ҳ�����ұ�ͼƬ
	 */
	private Drawable mClearDrawable;
	/**
	 * λ�ڿؼ�����ߵ�ͼƬ
	 */
	private Drawable mLeftDrawable;

	public DIYEditTextAccount(Context context) {
		super(context);
		init();
	}

	public DIYEditTextAccount(Context context, AttributeSet attrs) {
		super(context, attrs, android.R.attr.editTextStyle);
		init();
	}

	public DIYEditTextAccount(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// ���ñ���
		setBackgroundResource(R.drawable.background_edittext);
		mLeftDrawable = getCompoundDrawables()[0];
		mLeftDrawable = getResources().getDrawable(
				R.drawable.diyedit_login_account_icon);

		mLeftDrawable.setBounds(0, 0,
				(int) (mLeftDrawable.getIntrinsicWidth() * 0.65),
				(int) (mLeftDrawable.getIntrinsicHeight() * 0.65));

		mClearDrawable = getCompoundDrawables()[2];
		mClearDrawable = getResources().getDrawable(R.drawable.diyedit_clear);
		mClearDrawable.setBounds(0, 0,
				(int) (mClearDrawable.getIntrinsicWidth() * 0.65),
				(int) (mClearDrawable.getIntrinsicHeight() * 0.65));
		setClearDrawable();
		setLeftIconVisible();
		addTextChangedListener(this);
	}

	/**
	 * �������ͼ�����ʾ�����أ�����setCompoundDrawablesΪEditText������ȥ
	 * 
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], visible ? mClearDrawable : null,
				getCompoundDrawables()[3]);
	}

	/**
	 * �������ͼ�꣬����setCompoundDrawablesΪEditText������ȥ
	 * 
	 * @param visible
	 */
	protected void setLeftIconVisible() {
		setCompoundDrawables(mLeftDrawable, getCompoundDrawables()[1],
				getCompoundDrawables()[2], getCompoundDrawables()[3]);
	}

	/**
	 * ��������������ݷ����仯��ʱ��ص��ķ���
	 */
	@Override
	public void onTextChanged(CharSequence text, int start, int lengthBefore,
			int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		setClearDrawable();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void afterTextChanged(Editable s) {
		setClearDrawable();
	}

	// ����ɾ��ͼƬ
	private void setClearDrawable() {
		if (length() < 1)
			setClearIconVisible(false);
		else
			setClearIconVisible(true);
	}

	/**
	 * ���ɾ����ť����������
	 */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {
				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
						&& (event.getX() < ((getWidth() - getPaddingRight())));

				if (touchable) {
					this.setText("");
				}
			}
			this.setFocusable(true);
			this.setFocusableInTouchMode(true);
			this.requestFocus();
		}
		return super.onTouchEvent(event);
	}

}
