package net.bingyan.common.view;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 2bab on 15/4/25.
 * 用作字数限制的文字输入监听器
 */
public class LimitTextWatcher implements TextWatcher {

    private EditText editText;
    private TextView counter;
    private int maxLength;
    private int maxLines = 7;

    public LimitTextWatcher(EditText editText, int maxLength) {
        this.editText = editText;
        this.maxLength = maxLength;
    }

    public LimitTextWatcher(EditText editText, int maxLength, TextView counter) {
        this.editText = editText;
        this.maxLength = maxLength;
        this.counter = counter;

        counter.setText(maxLength + "");
    }

    public LimitTextWatcher(final EditText editText, final int maxLength, final int maxLines, TextView counter) {
        this.editText = editText;
        this.maxLength = maxLength;
        this.maxLines = maxLines;
        this.counter = counter;
        if (maxLines > 0) {
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN) {
                        if ( ((EditText)v).getLineCount() >= maxLines )
                            return true;
                    }

                    return false;
                }
            });
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int start = editText.getSelectionStart();
        int end = editText.getSelectionEnd();

        editText.removeTextChangedListener(this);
        if (!TextUtils.isEmpty(editText.getText())) {
            int count;
            while ((count = calculateLength(s.toString())) > maxLength) {
                s.delete(start - 1, end);
                start--;
                end--;
            }
            if (counter != null) {
                counter.setText((maxLength - count) + "");
            }
        } else {
            if (counter != null) {
                counter.setText(maxLength + "");
            }
        }

        editText.addTextChangedListener(this);
    }

    private int calculateLength(String text) {
        char[] ch = text.toCharArray();
        int varLength = 0;
        for (int i = 0; i < ch.length; i++) {
            if ((ch[i] >= 0x2E80 && ch[i] <= 0xFE4F) || (ch[i] >= 0xA13F && ch[i] <= 0xAA40) || ch[i] >= 0x80) {
                varLength = varLength + 2;
            } else {
                varLength++;
            }
        }
        return varLength;
    }
}
