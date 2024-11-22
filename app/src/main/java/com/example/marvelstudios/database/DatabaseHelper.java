package com.example.marvelstudios.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.TaskCompletionSource;

public class DatabaseHelper {
    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public DatabaseHelper() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    public void registerUser(User user, OnCompleteListener<Void> onCompleteListener) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        db.collection("users").document(user.getEmail()).set(user)
                                .addOnCompleteListener(onCompleteListener);
                    } else {
                        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
                        taskCompletionSource.setException(task.getException());
                        onCompleteListener.onComplete(taskCompletionSource.getTask());
                    }
                });
    }

    public void loginUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }
}
