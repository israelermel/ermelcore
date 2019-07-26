package br.com.israelermel.ermelcorelib.exceptions;

import android.content.Context;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import br.com.israelermel.ermelcorelib.R;
import br.com.israelermel.ermelcorelib.interfaces.MessageException;

public class LoginException implements MessageException {

    private Context mContext;

    public LoginException(Context context) {
        mContext = context;
    }

    public String getMessage(Throwable throwable) {
        String message = getString(R.string.exception_message_generic);

        try {
            throw throwable;
        } catch (FirebaseAuthInvalidCredentialsException ex1) {
            message = getString(R.string.msg_invalid_login);
        } catch (FirebaseAuthEmailException ex2) {
            message = "E-mail ou senha invalido";
        } catch (FirebaseAuthInvalidUserException ex3) {
            message = "Nenhuma conta vinculada a esse Email/Usuario";
        } catch (FirebaseAuthRecentLoginRequiredException ex4) {
            message = "FirebaseAuthRecentLoginRequiredException";
        } catch (FirebaseAuthUserCollisionException ex5) {
            message = "Conta ja cadastrada]";
        } catch (IllegalArgumentException ex6) {
            message = "Email/Usuário e senha são obrigatórios";
        } catch (SpCustomException ex7) {
            message = ex7.getMessage();
        } catch (FirebaseException ex8) {
            message = "Acao nao concluida com sucesso. Verifique os dados informados e tente novamente";
        }
        finally {
           return message;
        }
    }

    private Context getContext() {
        return mContext;
    }

    private String getString(int res) {
        try {
            return getContext().getString(res);
        } catch (NullPointerException e) {
            return "Houve uma falha inesperada. Por favor tente novamente";
        }

    }
}
