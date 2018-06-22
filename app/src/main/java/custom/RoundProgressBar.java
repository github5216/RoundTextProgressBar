package custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.custom.roundtextprogressbar.R;

import java.text.DecimalFormat;

import utils.DensityUtil;

/**
 * - @Description:
 * - @Author:  chenjianming
 * - @Time:  2018/4/8 14:05
 */
public class RoundProgressBar extends View {

    private int backgroundColor;
    private int progressColor;
    private int currentProgress;
    private int fromDegree;
    private int progressWidth;
    private Paint backgroundPaint;
    private Paint progressPaint;
    private TextPaint textPaint;
    private int textSize;
    private Context context1;
    private String text = "暂无数据";
    private float sweepAngle;

    public RoundProgressBar(Context context) {
        super(context);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public RoundProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        context1 = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        backgroundColor = typedArray.getColor(R.styleable.RoundProgressBar_backgroundColor, getResources().getColor(R.color.color_E5E5E5));
        progressColor = typedArray.getColor(R.styleable.RoundProgressBar_progressColor, getResources().getColor(R.color.colorAccent));
        currentProgress = typedArray.getInteger(R.styleable.RoundProgressBar_currentProgress, 100);
        fromDegree = typedArray.getInteger(R.styleable.RoundProgressBar_fromDegree, 0);
        progressWidth = typedArray.getInteger(R.styleable.RoundProgressBar_progressWidth, 10);
        textSize = typedArray.getInteger(R.styleable.RoundProgressBar_progressTextSize, 12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initPaint();
        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;
        canvas.drawCircle(xCenter, yCenter, xCenter - progressWidth, backgroundPaint);
        RectF rectF = new RectF(progressWidth, progressWidth, 2 * xCenter - progressWidth, 2 * yCenter - progressWidth);
        sweepAngle = (float) 360 / 100 * currentProgress;
        canvas.drawArc(rectF, -90, sweepAngle, false, progressPaint);
        StaticLayout layout = new StaticLayout(text, textPaint, 120, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
//        canvas.drawText(text, progressWidth, getWidth() / 2 - progressWidth, textPaint);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2 - layout.getHeight() / 2);
        layout.draw(canvas);
        canvas.restore();

    }

    public void setCurrentProgress(int progress, String text) {
        currentProgress = progress;
        this.text = text;
        postInvalidate();

    }

    private void initPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStrokeWidth(progressWidth);
        backgroundPaint.setStyle(Paint.Style.STROKE);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setStyle(Paint.Style.STROKE);

        textPaint = new TextPaint();
        textPaint.setColor(getResources().getColor(R.color.color_666666));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(DensityUtil.dip2px(context1, textSize));
    }
}
