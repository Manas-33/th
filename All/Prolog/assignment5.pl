% Define the individuals and their genders
female(queen_elizabeth).
female(princess_diana).
female(kate_middleton).
male(prince_charles).
male(prince_william).
male(prince_harry).

% Define parent-child relationships
parent(queen_elizabeth, prince_charles).
parent(prince_charles, prince_william).
parent(prince_charles, prince_harry).
parent(princess_diana, prince_william).
parent(princess_diana, prince_harry).
parent(kate_middleton, prince_george).

% Define marriage relationships
husband(prince_charles, queen_elizabeth).
wife(queen_elizabeth, prince_charles).
husband(prince_william, kate_middleton).
wife(kate_middleton, prince_william).

% Define sibling relationships
sister(princess_diana, queen_elizabeth).
brother(prince_charles, princess_diana).

% Define uncle and aunt relationships
paternal_uncle(prince_charles, prince_william).
paternal_uncle(prince_charles, prince_harry).
maternal_aunt(princess_diana, prince_george).

% Define cousin relationships
cousin_brother(prince_harry, prince_george).

% Define in-law relationships
soninlaw(prince_william, queen_elizabeth).
fatherinlaw(prince_charles, kate_middleton).

% Define predecessors
predecessor(X, Y) :- parent(X, Y).
predecessor(X, Y) :- parent(X, Z), predecessor(Z, Y).