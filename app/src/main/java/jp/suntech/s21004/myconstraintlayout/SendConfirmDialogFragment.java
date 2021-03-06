package jp.suntech.s21004.myconstraintlayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class SendConfirmDialogFragment extends DialogFragment {
    private final String toastText; // トーストのテキスト

    SendConfirmDialogFragment(String toastText) {
        this.toastText = toastText;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ダイアログビルダーを生成
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // ダイアログの種々の設定
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_msg);
        builder.setPositiveButton(R.string.dialog_btn_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_btn_ng, new DialogButtonClickListener());
        // ダイアログオブジェクトを生成し、リターン
        return builder.create();
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    // 確定がタップされたら送信内容を記述したトーストを表示
                    Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    // キャンセルがタップされたらキャンセルメッセージを記述したトーストを表示
                    Toast.makeText(getActivity(), "送信がキャンセルされました。", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
