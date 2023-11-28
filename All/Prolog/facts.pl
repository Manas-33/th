% Facts
likes(manas, mango).
likes(divesh, apple).
likes(sandesh, mango).
likes(akshay, apple).

% Rules
friends(X, Y):-likes(X, Z),likes(Y, Z).