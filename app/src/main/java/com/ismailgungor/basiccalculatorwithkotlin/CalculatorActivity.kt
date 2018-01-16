package com.ismailgungor.basiccalculatorwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

class CalculatorActivity : AppCompatActivity(), CalculatorActivityContract.View {

    @BindView(R2.id.et_cal_area)
    lateinit var etArea: EditText

    @BindView(R2.id.tv_cal_history)
    lateinit var tvHistory: TextView

    @BindView(R2.id.btn_cal_percentage)
    lateinit var btnPercentage: Button

    @Inject
    lateinit var mPresenter: CalculatorActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        DaggerCalculatorActivityComponent.builder()
                .calculatorActivityModule(CalculatorActivityModule())
                .build().inject(this)

        ButterKnife.bind(this)
        this.mPresenter.setView(this)
        btnPercentage.setOnClickListener {

            this.mPresenter.startPercentageProcess(this.etArea.text.toString())

        }
    }


    fun changeValue(view: View) {

        val selectedBtn = view as Button
        this.mPresenter.changeTextAreaValue(selectedBtn, etArea.text.toString())

    }

    fun changePlusMinus(view: View) {
        this.mPresenter.changePlusMinus(etArea.text.toString())
    }

    fun putDot(view: View) {
        this.mPresenter.putDot(etArea.text.toString())
    }

    fun deleteNumber(view: View) {
        this.mPresenter.deleteNumber(etArea.text.toString())
    }

    fun operation(view: View) {

        val selectedBtn = view as Button
        this.mPresenter.startOperationProcess(selectedBtn, etArea.text.toString())

    }

    fun calculateResult(view: View) {
        this.mPresenter.calculateResult(etArea.text.toString())
    }

    fun restart(view: View) {
        this.mPresenter.restartProcess()
    }

    override fun setTextArea(currentText: String) {

        this.etArea.setText(currentText)
    }

    override fun setHistoryTextArea(history: String) {
        this.tvHistory.setText(history)
    }


}
