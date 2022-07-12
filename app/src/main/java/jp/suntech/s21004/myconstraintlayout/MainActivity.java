package jp.suntech.s21004.myconstraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOnClickListener listener = new MyOnClickListener();
        // ボタンにリスナーを設定
        findViewById(R.id.btConfirm).setOnClickListener(listener);
        findViewById(R.id.btSend).setOnClickListener(listener);
        findViewById(R.id.btClear).setOnClickListener(listener);
    }

    // トーストに表示するためのテキストを返すメンバメソッド
    private String getInputText() {
        String name = ((EditText) findViewById(R.id.etName)).getText().toString();
        String mailTitle = ((EditText) findViewById(R.id.etMailTitle)).getText().toString();
        String mail = ((EditText) findViewById(R.id.etMail)).getText().toString();
        String comment = ((EditText) findViewById(R.id.etComment)).getText().toString();
        // 一つでも空欄があるならnullを返す
        if (name.isEmpty() || mailTitle.isEmpty() || mail.isEmpty() || comment.isEmpty()) {
            return null;
        }
        return "名前：" + name + "\nタイトル：" + mailTitle + "\nメールアドレス：" + mail + "\n質問内容：" + comment;
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String msg = getInputText();
            switch (view.getId()) {
                case R.id.btConfirm:
                    if (msg == null) {
                        Toast.makeText(MainActivity.this, "未入力の欄があります", Toast.LENGTH_LONG).show();
                        break;
                    }
                    // ダイアログ表示
                    new SendConfirmDialogFragment(msg).show(getSupportFragmentManager(), "OrderConfirmDialogFragment");
                    break;

                case R.id.btSend:
                    if (msg == null) {
                        Toast.makeText(MainActivity.this, "未入力の欄があります", Toast.LENGTH_LONG).show();
                        break;
                    }
                    // トースト表示
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    break;

                case R.id.btClear:
                    // 入力欄のクリア
                    ((EditText) findViewById(R.id.etName)).setText("");
                    ((EditText) findViewById(R.id.etMailTitle)).setText("");
                    ((EditText) findViewById(R.id.etMail)).setText("");
                    ((EditText) findViewById(R.id.etComment)).setText("");
                    break;
            }
        }
    }
}