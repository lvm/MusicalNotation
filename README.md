# MusicalNotation

A set of classes related to Musical Notation (Percussion, Note, Chord, Progression).

## Installation

a/ Clone this repository to your computer and copy the directory to the `Extension` directory (The path can be found by evaluating `Platform.userExtensionDir` or `Platform.systemExtensionDir`).

b/ Execute this -> `Quarks.install("https://github.com/lvm/MusicalNotation");`

## Usage

### Note

```
Note.at(\c);
-> 0

Note.g;
-> 7

Note.directory;
-> a: Semitone 9
af: Semitone 8
as: Semitone 10
b: Semitone 11
bf: Semitone 10
c: Semitone 0
cs: Semitone 1
d: Semitone 2
...

Note.names;
-> [ a, af, as, b, bf, c, cs, d, df, ds, e, ef, f, fs, g, gf, gs ]

[\c, \a, \f, \e].asNote;
-> [ 0, 9, 5, 4 ]

Pseq([\c, \a, \f, \e], inf).asStream.nextN(10);
-> [ 0, 9, 5, 4, 0, 9, 5, 4, 0, 9 ]
```

### Chord

```
Chord.at(\maj)
-> Chord([ 0, 4, 7 ], "Major")

Chord.maj
-> Chord([ 0, 4, 7 ], "Major")

Chord.choose(4);
-> Chord([ 0, 4, 6, 10 ], "7 Flat 5")

Chord.new(notes: [0, 1, 2], name: "Test chord")
-> Chord([ 0, 1, 2 ], "Test chord")

Chord.all.put(\test, Chord.new(notes: [0, 1, 2], name: "Test chord"));
-> IdentityDictionary[ (test -> Chord.test) ]

Chord.test;
-> Chord([ 0, 1, 2 ], "Test chord")

Chord.directory;
-> Add Eleven: add11 has 5 Notes
Add Thirteen: add13 has 5 Notes
Add 9: add9 has 4 Notes
Augmented: aug has 3 Notes
Diminished: dim has 3 Notes
Diminished 7th: dim7 has 4 Notes
Dominant 7th: dom7 has 4 Notes
Eleven: eleven has 6 Notes
...

Chord.names;
-> [ sixSus4, m9, sevenSharp9, maj11, m7, sevenFlat5, dom7, m6add9, m7dim, m7Flat5, one, dim, sus4, six, m11, maj, majAdd13, m7Flat9, mMaj7, mMaj9, sevenSharp5, dim7, mMaj11, add11, maj9Sus4, m6, eleven, mAdd13, mMajAdd11, sevenFlat9, aug, m13, sevenSus4, maj7Sus4, maj7, sevenSharp5Flat9, sus2, thirteen, mAdd11, m7Sharp5, add13, nineSharp11, madd9, min, majAdd11, mMaj13, nine, maj9, nineFlat13, add9, sixadd9, five, maj13, mMajAdd13, nineSus4 ]

Pseq([\maj, \min], inf).asStream.nextN(10);
-> [ [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 4, 7 ], [ 0, 3, 7 ] ]
```

### Progression

```
Progression.at(\sad);
-> Progression([ 0, 3, 4, 4 ], "Sad")

Progression.sad;
-> Progression([ 0, 3, 4, 4 ], "Sad")

Progression.new(degrees: [2, 3, 1, 5], name: "Weird");
-> Progression([ 2, 3, 1, 5 ], "Weird")

Progression.all.put(\weird, Progression.new(degrees: [2, 3, 1, 5], name: "Weird"));
-> IdentityDictionary[ (weird -> Progression.weird) ]

Progression.weird;
-> Progression([ 2, 3, 1, 5 ], "Weird")

Progression.sad.roman;
-> i-iv-v-v

Progression.directory;
-> Ballad: ballad i-i-iv-vi
Ballad Alt: balladAlt i-iv-vi-v
Blues: blues i-iv-i-v-i
Blues Alt: bluesAlt i-i-i-i-iv-iv-i-i-v-iv-i-i
Creepy: creepy i-vi-iv-v
Creepy Alt: creepyAlt i-vi-ii-v
50s: fifties i-vi-iv-v
General: gral i-iv-v-i
...

Progression.names;
-> [ ballad, balladAlt, blues, bluesAlt, creepy, creepyAlt, eleven, elevenb, elevenc, elevend, fifties, gral, gralAlt, nrg, pop, rebel, rock, rockAlt, roll, sad, unresolved ]


Scale.major.chordProgression(\sad, \c);
-> [ [ 0, 4, 7 ], [ 5, 9, 12 ], [ 7, 11, 14 ], [ 7, 11, 14 ] ]

Scale.major.chordProgression(\weird, \g);
-> [ [ 11, 14, 18 ], [ 12, 16, 19 ], [ 9, 12, 16 ], [ 16, 19, 23 ] ]
```

### Percussion

```
Percussion.at(\bd);
-> 36

Percussion.bd;
-> 36

Percussion.directory;
-> abd: MIDI Note 35
ag: MIDI Note 68
bd: MIDI Note 36
be: MIDI Note 53
bt: MIDI Note 84
ca: MIDI Note 69
cas: MIDI Note 85
ch: MIDI Note 42
...

Percussion.names;
-> [ abd, ag, bd, be, bt, ca, cas, ch, cl, cow, cp, cr, cr2, cy, esn, gui, hag, hb, hc, hft, hh, hmt, hq, ht, hti, hwb, jb, lag, lb, lc, lft, lgui, lmt, lt, lti, lwb, lwi, ma, mb, mc, mcl, mhc, ms, mt, oc, oh, ohc, os, ot, ph, ri, ri2, rm, scr, scy, sgui, sha, sl, sn, spl, sps, sqc, st, sti, swi, ta, vib, whi ]

[\bd, \sn, \ch, \oh].asPercussion;
-> [ 36, 38, 42, 46 ]

Pseq([\bd, \sn, \ch, \oh], inf).asStream.nextN(10);
-> [ 36, 38, 42, 46, 36, 38, 42, 46, 36, 38 ]
```

## Reference

* https://www.midi.org/specifications/item/gm-level-1-sound-set
* https://en.wikipedia.org/wiki/General_MIDI_Level_2#Drum_sounds

## License

[LICENSE](LICENSE)
