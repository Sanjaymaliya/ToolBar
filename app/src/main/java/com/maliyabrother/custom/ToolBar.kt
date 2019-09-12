package com.maliyabrother.custom
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.maliyabrother.custom.extensions.gone
import com.maliyabrother.custom.extensions.visible


class ToolBar : LinearLayout {

    private lateinit var toolbarTitle: TextView
    private lateinit var toolbarLeftIcon: ImageView
    private lateinit var toolbarRightIcon: ImageView
    private lateinit var toolbarLeftText: TextView
    private lateinit var toolbarRightText: TextView
    private lateinit var toolbar: RelativeLayout
    private val defaultTextColor = android.R.color.black
    private val defaultBgColor = android.R.color.white

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, android.R.attr.textViewStyle)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @SuppressLint("ResourceAsColor")
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.toolbar, this)
        toolbarTitle = findViewById(R.id.toolbar_title)
        toolbarLeftIcon = findViewById(R.id.toolbarLeftIcon)
        toolbarRightIcon = findViewById(R.id.toolbarRightIcon)
        toolbarLeftText = findViewById(R.id.toolbarLeftText)
        toolbarRightText = findViewById(R.id.toolbarRightText)
        toolbar= findViewById(R.id.toolbar)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar, defStyleAttr, 0)
        try {
            if (typedArray.hasValue(R.styleable.ToolBar_android_text)) {
                val str = typedArray.getString(R.styleable.ToolBar_android_text)
                toolbarTitle.text = str
            }
            if (typedArray.hasValue(R.styleable.ToolBar_android_textSize)) {
                val textSize = typedArray.getDimension(R.styleable.ToolBar_android_textSize, 0f)
                val density = resources.displayMetrics.density
                toolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize / density)
            }
            if (typedArray.hasValue(R.styleable.ToolBar_rightDrawable)) {
                val rightDrawable = typedArray.getDrawable(R.styleable.ToolBar_rightDrawable)
                toolbarRightIcon.setImageDrawable(rightDrawable)
                toolbarRightIcon.visible()
            } else {
                toolbarRightIcon.gone()
            }

            if (typedArray.hasValue(R.styleable.ToolBar_leftDrawable)) {
                val leftDrawable = typedArray.getDrawable(R.styleable.ToolBar_leftDrawable)
                toolbarLeftIcon.setImageDrawable(leftDrawable)
                toolbarLeftIcon.visible()
            } else {
                toolbarLeftIcon.gone()
            }

            if (typedArray.hasValue(R.styleable.ToolBar_rightText)) {
                val str = typedArray.getString(R.styleable.ToolBar_rightText)
                toolbarRightText.text = str
                toolbarRightText.visible()
            } else {
                toolbarRightText.gone()
            }

            if (typedArray.hasValue(R.styleable.ToolBar_leftText)) {
                val str = typedArray.getString(R.styleable.ToolBar_leftText)
                toolbarLeftText.text = str
                toolbarLeftText.visible()
            } else {
                toolbarLeftText.gone()
            }

            if (typedArray.hasValue(R.styleable.ToolBar_toolbarTextColor)) {
                val textColor = typedArray.getColor(R.styleable.ToolBar_toolbarTextColor,ContextCompat.getColor(context, defaultTextColor))
                toolbarRightText.setTextColor(textColor)
                toolbarLeftText.setTextColor(textColor)
                toolbarTitle.setTextColor(textColor)
            }
            if (typedArray.hasValue(R.styleable.ToolBar_toolbarBackgroundColor)) {
                val bgColor = typedArray.getColor(R.styleable.ToolBar_toolbarBackgroundColor,ContextCompat.getColor(context, defaultBgColor))
                toolbar.setBackgroundColor(bgColor)
            }

        } finally {
            typedArray.recycle()
        }
    }

    fun setToolbarTitle(toolbarTitle: CharSequence) {
        this.toolbarTitle.text = toolbarTitle
    }

    fun setRightToolbarTitle(toolbarRightTitle: CharSequence) {
        this.toolbarRightText.text = toolbarRightTitle
    }

    fun setLeftToolbarTitle(toolbarLeftTitle: CharSequence) {
        this.toolbarLeftText.text = toolbarLeftTitle
    }

    fun setLeftIconListener(listener: View.OnClickListener?) {
        toolbarLeftIcon.setOnClickListener(listener)
    }

    fun setRightIconListener(listener: View.OnClickListener?) {
        toolbarRightIcon.setOnClickListener(listener)
    }

    fun setLeftTextListener(listener: View.OnClickListener?) {
        toolbarLeftText.setOnClickListener(listener)
    }

    fun setRightTextListener(listener: View.OnClickListener?) {
        toolbarRightText.setOnClickListener(listener)
    }
}
