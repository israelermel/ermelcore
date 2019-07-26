package br.com.israelermel.ermelcorelib.utils;

import android.app.Activity;
import android.text.InputType;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import br.com.israelermel.ermelcorelib.R;
import br.com.israelermel.ermelcorelib.exceptions.SpCustomException;
import br.com.israelermel.ermelcorelib.interfaces.BaseDialog;
import io.reactivex.Single;

public class DialogsUtils implements BaseDialog {

    private Activity mActivity;
    private MaterialDialog mDialogProgress;

    public DialogsUtils(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void warning(int content) {

        new MaterialDialog.Builder(mActivity)
                .title("Atencao")
                .content(content)
                .positiveText("OK")
                .negativeText("Cancelar")
                .show();
    }

    public void warning(String message) {
        new MaterialDialog.Builder(mActivity)
                .title("Atencao")
                .content(message)
                .positiveText("OK")
                .negativeText("Cancelar")
                .show();
    }

    public MaterialDialog warning(String title, String content, String negativeText, String positiveText, OnClickListener listener) {
        return new MaterialDialog.Builder(mActivity)
                    .title(title)
                    .content(content)
                    .positiveText(positiveText)
                    .negativeText(negativeText)
                    .onPositive((dialog, which) -> listener.positive(dialog, which))
                    .onNegative((dialog, which) -> listener.negative(dialog, which))
                    .build();
    }

    @Override
    public MaterialDialog getProgress() {
        return new MaterialDialog.Builder(mActivity)
                .title(R.string.text_aguarde)
                .progress(true, 0)
                .build();

    }

    @Override
    public void showProgress() {
        initDialogProgress();
        mDialogProgress.show();
    }

    private void initDialogProgress() {
        if (mDialogProgress == null) {
            mDialogProgress =  new MaterialDialog.Builder(mActivity)
                    .title(R.string.text_aguarde)
                    .progress(true, 0)
                    .build();
        }

    }

    @Override
    public void hideProgress() {
        if (mDialogProgress != null) {
            if (mDialogProgress.isShowing()) {
                mDialogProgress.hide();
            }
        }
    }

    @Override
    public Single<String> input(String prefill) {
        return Single.create(emitter -> new MaterialDialog.Builder(mActivity)
                .title(R.string.dl_title_password_reset)
                .negativeText(R.string.action_dl_cancel)
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .input(getString(R.string.email), prefill, (dialog, input) -> {
                    if (input.length() > 0) {
                        dialog.dismiss();
                        emitter.onSuccess(input.toString());
                    } else {
                        emitter.tryOnError(new SpCustomException("E-mail é obrigatorio"));
                    }
                }).show());

    }

    private String getString(int res) {
        return mActivity.getString(res);
    }


    public interface OnClickListener {
        void positive(MaterialDialog dialog, DialogAction which);
        void negative(MaterialDialog dialog, DialogAction which);
    }
}




































/*

 package br.com.businessshop.atendimento.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import br.com.businessshop.atendimento.R;
import br.com.businessshop.atendimento.interfaces.Dialog;

public class BSDialogUtils implements Dialog {

    private Activity mActivity;
    private MaterialDialog mErroConexao;

    public BSDialogUtils(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void success(String titleText, String contentText , OnClickListener listener) {
        MaterialDialog build = new MaterialDialog.Builder(mActivity)
                .title(titleText)
                .content(contentText)
                .positiveText(getString(R.string.dialog_positive_text))
                .neutralText(getString(R.string.dialog_text_cancelar))
                .positiveColor(mActivity.getResources().getColor(R.color.btnOK))
                .neutralColor(mActivity.getResources().getColor(R.color.btnCancelar))
                .onPositive((dialog, which) -> listener.onClick())
                .build();

        build.getActionButton(DialogAction.POSITIVE).setTextSize(24);
        build.getActionButton(DialogAction.NEUTRAL).setStackedGravity(GravityEnum.CENTER);
        build.getActionButton(DialogAction.NEUTRAL).setTextSize(18);

        build.show();
    }

    @Override
    public void success(String titleText, String contentText) {
        new MaterialDialog.Builder(mActivity)
                .title(titleText)
                .content(contentText)
                .positiveText(getString(R.string.dialog_positive_text_ok))
                .positiveColor(mActivity.getResources().getColor(R.color.btnOK))
                .show();
    }

    @Override
    public void error(String contentText){
        new MaterialDialog.Builder(mActivity)
                .title(getString(R.string.dialog_title_atencao))
                .content(contentText)
                .positiveText(getString(R.string.dialog_positive_text_ok))
                .icon(mActivity.getResources().getDrawable(R.drawable.ic_error_outline_24dp))
                .show();
    }

    @Override
    public void error(String contentText, OnClickListener listener){
        new MaterialDialog.Builder(mActivity)
                .title(getString(R.string.dialog_title_atencao))
                .content(contentText)
                .positiveText(getString(R.string.dialog_positive_text_ok))
                .onPositive((dialog, which) -> listener.onClick())
                .icon(mActivity.getResources().getDrawable(R.drawable.ic_error_outline_24dp))
                .show();
    }


    @Override
    public void erroConexaoRede(){
        if (mErroConexao == null) {
            mErroConexao = new MaterialDialog.Builder(mActivity)
                    .title(getString(R.string.dialog_title_atencao))
                    .content(getString(R.string.falha_conexao_rede))
                    .positiveText(getString(R.string.dialog_positive_text_ok))
                    .icon(mActivity.getResources().getDrawable(R.drawable.ic_error_outline_24dp))
                    .build();
        }

        if (!mErroConexao.isShowing()) {
            mErroConexao.show();
        }

    }


    @Override
    public void warning(String contentText){
        new MaterialDialog.Builder(mActivity)
                .title(getString(R.string.dialog_title_atencao))
                .content(contentText)
                .positiveText(getString(R.string.dialog_positive_text_ok))
                .icon(mActivity.getResources().getDrawable(R.drawable.ic_warning_24dp))
                .show();
    }

    @Override
    public void confirm(String contentText, OnClickListener listener){
        new MaterialDialog.Builder(mActivity)
                .title(R.string.dialog_confirm_title_remocao)
                .content(contentText)
                .positiveText(getString(R.string.dialog_btn_remover))
                .icon(mActivity.getResources().getDrawable(R.drawable.ic_warning_24dp))
                .onPositive((dialog, which) -> listener.onClick())
                .negativeText(getString(R.string.dialog_text_cancelar))
                .show();
    }

    @Override
    public MaterialDialog getProgress() {
        MaterialDialog show = new MaterialDialog.Builder(mActivity)
                .title(R.string.text_aguarde)
                .progress(true, 0)
                .build();

        return show;
    }

    public static void settings(Activity activity, String confirmTitle, String confirmText, OnClickDialogUtils listener) {
        new MaterialDialog.Builder(activity)
                .title( confirmTitle )
                .content( confirmText )
                .negativeText( activity.getString(R.string.dialog_text_cancelar) )
                .positiveText( activity.getString(R.string.text_abrir_config) )
                .icon(activity.getResources().getDrawable(R.drawable.ic_warning_24dp))
                .onPositive((dialog, which) -> listener.onPositive())
                .show();
    }

    @Override
    public <T extends Parcelable> void chooseRadio(String confirmTitle, String confirmText, List<T> lists , OnClickListener listener){
        new MaterialDialog.Builder(mActivity)
                .title(confirmTitle)
                .content(confirmText)
                .items(lists)
                .icon(mActivity.getResources().getDrawable(R.drawable.ic_warning_24dp))
                .onPositive((dialog, which) -> listener.onClick())
                .negativeText(getString(R.string.dialog_text_cancelar))
                .show();
    }

    @Override
    public MaterialDialog setAdapter(String title, RecyclerView.Adapter adapter) {
        return new MaterialDialog.Builder(mActivity)
                .title(title)
                .titleGravity(GravityEnum.CENTER)
                .adapter(adapter, null)
                .build();
    }

    @Override
    public MaterialDialog getCustomDialog(View view, int title) {
        return new MaterialDialog.Builder( mActivity )
                .title(title)
                .titleGravity(GravityEnum.CENTER)
                .customView(view, true)
                .build();
    }

    @Override
    public Context getContext() {
        return mActivity.getBaseContext();
    }

    @Override
    public String getString(int res) {
        return mActivity.getString(res);
    }

    public interface OnClickListener {
        void onClick();
    }

    public interface OnClickDialogUtils {
        void onPositive();
    }
}


 */
