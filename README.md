# CardShuffler
Sample application that makes use of Deck of Cards API. It lets you choose a number of decks that are going to be used for drawing 5 cards. Next, those cards are validated if there are one of the following variants found:
+ Color - at least 3 cards are of the same suit
+ Stairs - at least 3 cards are rising or falling (eg. 4, 5, 6 or Jack, 10, 9)
+ Figure - at least 3 cards are figures (eg. Jack, Queen, King)
+ Twins - at least 3 cards have the same value (eg. 3x King)

When there are less than 5 cards in deck, you can shuffle them.

# Architecture and libraries
Application is written in the MVVM architecture. It uses following libraries:
+ RxJava2 (and RxAndroid2)
+ Retrofit2
+ Dagger2
+ Databinding
+ Glide

# Screenshots
![screen](/screenshots/screen_welcome.png) ![screen](/screenshots/screen_cards.png)

# TODO
Tests