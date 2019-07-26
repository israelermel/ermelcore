package br.com.israelermel.ermelcorelib.rest.login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.com.israelermel.ermelcorelib.exceptions.LoginCreateException;
import io.reactivex.Observable;
import io.reactivex.Single;

public class LoginDaoImpl implements LoginDAO {

    private FirebaseAuth mAuth;
    private Activity mActivity;

    public LoginDaoImpl(FirebaseAuth auth, Activity activity) {
        mAuth = auth;
        mActivity = activity;
    }

    @Override
    public Observable<FirebaseUser> createUser(String email, String password) {
        return Observable.create(emitter -> {

            if (emitter.isDisposed()) {
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mActivity, task -> {
                        if (task.isSuccessful()) {
                            emitter.onNext(mAuth.getCurrentUser());
                            emitter.onComplete();
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });

        });
    }

    @Override
    public Single<FirebaseUser> signIn(String email, String password) {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(mActivity, task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(mAuth.getCurrentUser());
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });
        });
    }

    @Override
    public Single<Boolean> sendPasswordResetEmail(String email) {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(true);
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });
        });
    }

    @Override
    public Single<Boolean> deleteAccount(FirebaseUser user) {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            user.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(true);
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });
        });
    }

    @Override
    public Single<Boolean> updatePassword(FirebaseUser user, String newPassword) {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(true);
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });
        });
    }

    @Override
    public Single<Boolean> updateProfile(FirebaseUser user, UserProfileChangeRequest profile) {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            user.updateProfile(profile)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(true);
                        } else {
                            if (task.getException() != null) {
                                emitter.onError(task.getException());
                            } else {
                                emitter.onError(new LoginCreateException());
                            }
                        }
                    });
        });
    }

    @Override
    public Single<Boolean> signOut() {
        return Single.create(emitter -> {
            if (emitter.isDisposed()) {
                return;
            }

            if (mAuth != null) {
                mAuth.signOut();
                emitter.onSuccess(true);
            } else {
                emitter.onError(new LoginCreateException());
            }
        });

    }


}
