feedback on QuickStart, if time permits, improvements that i’d like to see, please:

[1] custom id only
[2] use production mode without filtering
[3] show how to do counts with 6.0's fancy new analytics traversal (should kick off graph frames)
[4] show an example of how to add data via traversals, like folks do via the driver
[5] add a profile cell with an example of how to “do” performance improvements
does that help? this is a small wish list … you have autonomy on what can be accomplished for 6.0

Lorina Poland Sure - I already have 1,4,5 planned. 3 should be doable. 2 is the only thing that is difficult. Most of the queries used to explore the data require filtering.

Jonathan Lacefield
maybe make more notes around #2 ?  and show how to use indexes or analytics modes to avoid filtering
Lorina Poland OK.

Jonathan Lacefield would like users to avoid the experience of learning about Scans on their own if possible
Lorina Poland
Yes, I can work in some prod vs dev stuff.  It’s just hard - basic things require filtering.
For instance, counts to check that all data has been loaded. All vertices of a particular type.

Jonathan Lacefield yes … we’re currently recommending either Search or Analytics for those

Lorina Poland
OK. I guess that brings up a good question - at the most basic, learn what Graph is level, how much search or analytics do you want to bring into the picture?
Sounds like you expect it up front.
And a necessary element for the most basic Graph functionality.
