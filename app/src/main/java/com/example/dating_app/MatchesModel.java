package com.example.dating_app;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchesModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatch(Match m) {
        CollectionReference matchesRef = db.collection("matches");
        matchesRef.add(m);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback,
                           Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchById(Match m) {
        DocumentReference matchesRef = db.collection("matches").document(m.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("name", m.name);
        data.put("liked", m.liked);
        data.put("imageUrl", m.imageUrl);
        data.put("lat", m.lat);
        data.put("longitude", m.longitude);
        matchesRef.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }
}

