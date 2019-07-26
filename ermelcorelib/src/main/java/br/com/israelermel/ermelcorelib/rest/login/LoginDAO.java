package br.com.israelermel.ermelcorelib.rest.login;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface LoginDAO {
    Observable<FirebaseUser> createUser(String email, String password);
    Single<FirebaseUser> signIn(String email, String password);
    Single<Boolean> sendPasswordResetEmail(String email);
    Single<Boolean> deleteAccount(FirebaseUser user);
    Single<Boolean> updatePassword(FirebaseUser user, String newPassword);
    Single<Boolean> updateProfile(FirebaseUser user, UserProfileChangeRequest profile);
    Single<Boolean> signOut();
}
