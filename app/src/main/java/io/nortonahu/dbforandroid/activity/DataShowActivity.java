package io.nortonahu.dbforandroid.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.nortonahu.dbforandroid.R;
import io.nortonahu.dbforandroid.app.AppContext;
import io.nortonahu.dbforandroid.bean.Person;

/**
 * Author:    Hong Yu
 * Version    V1.0
 * Date:      2015/9/25 09:10.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * Why & What is modified:
 */
public class DataShowActivity extends BaseAppActivity {

    @Bind(R.id.data_show_view)
    TextView mTextView;

    List<Person> mPersonsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_show_view);
        ButterKnife.bind(this);
        initView();
        initDate();
    }

    @Override
    protected void initView() {
        setTitle(AppContext.string(R.string.data_show_title_zh));
    }

    @Override
    protected void initDate() {
        mPersonsData = mDBManager.query();
        if(mPersonsData == null || mPersonsData.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("信息\n");
        int count = 0;
        for(Person person:mPersonsData) {
            sb.append(person.toString());
            if(count++ != mPersonsData.size()) {
                sb.append("\n");
            }
        }
        mTextView.setText(sb.toString());
    }


}
