/*
 * Copyright (C) 2018 Pramod Garg
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.pramodgarg.mark6.ui.sample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.github.pramodgarg.mark6.BR
import com.github.pramodgarg.mark6.R
import com.github.pramodgarg.mark6.data.model.Repo
import com.github.pramodgarg.mark6.databinding.ActivityListRepoBinding
import com.github.pramodgarg.mark6.ui.base.BaseActivity
import com.github.pramodgarg.mark6.ui.sample.viewmodel.ListRepoViewModel
import com.github.pramodgarg.mark6.ui.sample.viewmodel.ListViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class ListRepoActivity : BaseActivity<ActivityListRepoBinding, ListRepoViewModel>() {

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_list_repo

    override fun getViewModel() = ViewModelProviders.of(this, factory).get(ListRepoViewModel::class.java)

    @Inject
    lateinit var factory: ListViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        mViewDataBinding.rvItems.layoutManager = LinearLayoutManager(this)
        mViewModel.repo.observe(this, Observer<List<Repo>> { updateAdapterList(it) })
    }

    private fun updateAdapterList(list: List<Repo>?) {
        if (list == null) {
            mViewDataBinding.rvItems.adapter = TrackRecyclerAdapter(emptyList())
        } else {
            mViewDataBinding.rvItems.adapter = TrackRecyclerAdapter(list)
        }
    }
}