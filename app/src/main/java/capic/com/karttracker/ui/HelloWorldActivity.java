package capic.com.karttracker.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import capic.com.karttracker.R;

public class HelloWorldActivity extends MvpAppCompatActivity implements HelloWorldView {

    @InjectPresenter
    HelloWorldPresenter mHelloWorldPresenter;

    @BindView(R.id.activity_hello_world_text_view_message)
    TextView mHelloWorldTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        ButterKnife.bind(this);

        mHelloWorldPresenter.setMessage(11111);
    }

    @Override
    public void showMessage(int message) {
        mHelloWorldTextView.setText(String.valueOf(message));
    }
}
