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

package com.github.pramodgarg.mark6.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.github.pramodgarg.mark6.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by pramod on 22/03/18.
 */
abstract class BaseViewModel(
        private val mCompositeDisposable: CompositeDisposable,
        private val mSchedulerProvider: SchedulerProvider,
        private val mIsLoading: ObservableBoolean = ObservableBoolean(false)) : ViewModel() {

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}