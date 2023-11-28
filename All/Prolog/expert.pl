% Facts
symptom(john, fever).
symptom(john, fatigue).
symptom(john, headache).
symptom(mary, fever).
symptom(mary, cough).
symptom(mary, sore_throat).
symptom(dave, fever).
symptom(dave, fatigue).
symptom(dave, headache).

% Rules
has_prologitis(X) :-
    symptom(X, fever),
    symptom(X, fatigue),
    symptom(X, headache).

has_flu(X) :-
    symptom(X, fever),
    symptom(X, cough).

% Diagnosis
diagnose(X) :-
    has_prologitis(X),
    write(X), write(' probably has Prologitis.').

diagnose(X) :-
    has_flu(X),
    write(X), write(' probably has the flu.').

diagnose(X) :-
    write('Cannot diagnose the condition for '), write(X), write(' at the moment.').