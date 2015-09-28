package io.nortonahu.dbforandroid.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nortonahu.dbforandroid.R;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/28 10:27.
 * Description: 仿微信展开测试
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class TextViewUnfoldActivity extends BaseAppActivity {
    @Bind(R.id.content_view)
    TextView mTextView;

    @Bind(R.id.input_view)
    EditText mEditText;

    @Bind(R.id.add_content)
    Button mAddContent;

    @Bind(R.id.line_count)
    Button mLineCount;

    @Bind(R.id.expand)
    Button mExpandView;

    private static final int MAX_LINE_COUNT = 2;
    StringBuilder sb = new StringBuilder();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unfold_textview_view);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("全文扩展");
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                mTextView.setText(s.toString());
            }
        });
        mTextView.setText("这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据这是测试数据");
        mTextView.setHeight(mTextView.getLineHeight() * MAX_LINE_COUNT);
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                mExpandView.setVisibility(mTextView.getLineCount() > MAX_LINE_COUNT ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void initDate() {

    }

    @OnClick(R.id.add_content)
    public void addContent() {
//        sb.append("这是测试数据");
//        mTextView.append("这是测试数据");
//        mTextView.set
    }

    @OnClick(R.id.line_count)
    public void getLineCount() {
//        mTextView.append("这是测试数据");
        Toast.makeText(this,"行数：" + mTextView.getLayout().getLineCount(), Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.expand)
    public void expandView(Button view) {
        if(view.getText().equals("收起"))
        {
            mTextView.setHeight(mTextView.getLineHeight() * MAX_LINE_COUNT);
            view.setText("全文");
        }else
        {
            mTextView.setHeight(mTextView.getLineHeight() * mTextView.getLineCount());
            view.setText("收起");
        }
//        switch (view.getVisibility()) {
//            case View.VISIBLE:
//                mTextView.setHeight(mTextView.getLineHeight() * mTextView.getLineCount());
//                view.setText("收起");
//                break;
//            case View.GONE:
//            case View.INVISIBLE:
//                mTextView.setHeight(mTextView.getLineHeight() * MAX_LINE_COUNT);
//                view.setText("全文");
//                break;
//            default:
//                break;
//        }
    }

}
