package com.lutfiseptian.test.kanggo.ui.main

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lutfiseptian.test.kanggo.base.BaseActivity
import com.lutfiseptian.test.kanggo.data.state.MovieState
import com.lutfiseptian.test.kanggo.databinding.ActivityDashboardBinding
import com.lutfiseptian.test.kanggo.model.DataMovie
import com.lutfiseptian.test.kanggo.ui.adapter.MovieAdapter
import com.lutfiseptian.test.kanggo.ui.detail.DetailMoviePage
import com.lutfiseptian.test.kanggo.ui.favorite.FavouritePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {


    private val viewModel: MainViewModel by viewModel()

    private val adapter  by lazy {
        MovieAdapter { item -> showDetail(item)}
    }

    private val binding  by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun setLayout()= binding.root



    override fun onCreateActivity(savedInstanceState: Bundle?) {

        setupUI()
    }

    private fun setupUI(){
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }
        with(binding){
            rvData.also {
                it.adapter = adapter
                it.layoutManager = LinearLayoutManager(this@MainActivity)
                it.setHasFixedSize(true)
            }


            CoroutineScope(Dispatchers.IO).launch {
                fabFav.setOnClickListener {
                    openActivity(FavouritePage::class.java)
                }
            }
        }

        viewModel.getMovieData()
        setupViewModel()


    }

    private fun setupViewModel(){
        viewModel.callback.observe(this,{
            when(it){
                is MovieState.Loading -> setLoading(true)
                is MovieState.Result -> setLoading(false)
                is MovieState.Error -> showError(refreshClick = {
                    setupViewModel()
                })
            }
        })
        viewModel.data.observe(this, Observer(adapter::submitList))
    }

    private fun setLoading(isload: Boolean){
        with(binding){
            if(isload){
                show(shimmerMovie)
                hide(rvData)
            }else{
                hide(shimmerMovie)
                show(rvData)
            }
        }
    }

    private fun showDetail(item: DataMovie) {
        openActivity(DetailMoviePage::class.java, extras = {
            this.putParcelable("data",item)
        })
    }

}