package com.ad8.presentation.base

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ad8.presentation.R
import com.ad8.presentation.util.extentions.hideKeyboard
import kotlinx.coroutines.*
import java.util.*


abstract class BaseFragment<VDB : ViewDataBinding>(
    @LayoutRes private val resId: Int
) : Fragment() {

    abstract fun expireToken()

    companion object {
        var isShowingExpireDialog: Boolean = false
    }




    /* fun showAlert(
         title: String = getString(R.string.attention),
         message: String = "",
         positiveCaption: String = getString(R.string.finish),
         negativeCaption: String = getString(R.string.no),
         positiveAction: View.OnClickListener? = null,
         negativeAction: View.OnClickListener? = null
     ) {
         if (!BaseFragment.isShowingExpireDialog) {
             MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                 .setTitle(title)
                 .setMessage(message)
                 .setOnDismissListener { BaseFragment.isShowingExpireDialog = false }
                 .setPositiveButton(
                     positiveCaption
                 ) { dialog, _ ->
                     positiveAction?.onClick(view)
                     dialog.dismiss()
                 }
                 .setNegativeButton(negativeCaption) { dialog, _ ->
                     negativeAction?.onClick(view)
                     dialog.dismiss()
                 }
                 .create().show()
             BaseFragment.isShowingExpireDialog = true
         }

     }
 */


    lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, resId, container, false
        )
        return binding.root
        //return inflater.inflate(resId, container, false)
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        observe(this@BaseFragment, Observer {
            it?.let { observer(it) }
        })
    }


    fun dpToPx(dp: Float): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        requireContext().resources.displayMetrics
    ).toInt()


    /*  if (onClickListener != null) {
          val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
              .setAction(getString(R.string.try_again), onClickListener)
          val fab: FloatingActionButton = (activity as MainActivity).binding.fab
          if (fab.visibility == View.VISIBLE)
              snackBar.anchorView = (activity as MainActivity).binding.fab
          snackBar.show()
      } else {
          val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
          val fab: FloatingActionButton = (activity as MainActivity).binding.fab
          if (fab.visibility == View.VISIBLE)
              snackBar.anchorView = (activity as MainActivity).binding.fab
          snackBar.show()
      }*/


    open fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context: Context = recyclerView.context
        val controller: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        recyclerView.layoutAnimation = controller
        //recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }


    fun runBonusLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom)
        recyclerView.layoutAnimation = controller
        //recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
    }

}