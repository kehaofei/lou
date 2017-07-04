/*
 * Copyright  (c) 2017 Lyloou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyloou.test.onearticle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lyloou.test.R;
import com.lyloou.test.common.NetWork;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OneArticleRandomActivity extends AppCompatActivity {

    Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_onearticle);

        initView();

        loadData();
    }


    private void loadData() {
        Observable<OneArticle> observable = NetWork.getOneArticleApi().getRandomArticle(1);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OneArticle>() {
                               @Override
                               public void accept(@NonNull OneArticle oneArticle) throws Exception {
                                   TextView tvTitle = findViewById(R.id.tv_title);
                                   TextView tvAuthorDate = findViewById(R.id.tv_author_date);
                                   TextView tvContent = findViewById(R.id.tv_content);
                                   tvTitle.setText(oneArticle.getData().getTitle());
                                   tvAuthorDate.setText(oneArticle.getData().getAuthor() + "（" + oneArticle.getData().getDate().getCurr() + "）");
                                   tvContent.setText(Html.fromHtml(oneArticle.getData().getContent()));
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                                Toast.makeText(mContext, "加载失败：" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_onearticle);
        toolbar.setTitle("每日一文");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.coolapsing_toolbar_layout_onearticle);
        collapsingToolbarLayout.setExpandedTitleColor(Color.YELLOW);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        ImageView ivHeader = collapsingToolbarLayout.findViewById(R.id.iv_header);

        int image = (int) (98 * Math.random() + 1);
        String url = "https://meiriyiwen.com/images/new_feed/bg_" + image + ".jpg";
        Glide.with(mContext).load(url).into(ivHeader);
    }
}
