/*
Chord class

(c) 2018 by Mauro <mauro@sdf.org>
http://www.cyberpunk.net.ar/

Similar to Scales / Tuning classes but for Chords.

*/

Chord {
  var <notes, <>name;
  classvar <all;

  *new { |notes, name = "Unknown chord"|
    ^super.new.init(notes, name);
  }
  init { |inNotes, inName|
    notes = inNotes;
    name = inName ?? "Unknown chord";
    ^this;
  }

  *initClass {
    all = IdentityDictionary[
      \one -> Chord.new(notes: [0], name: "Single note"),
      \maj -> Chord.new(notes: [0, 4, 7], name: "Major"),
      \min -> Chord.new(notes: [0, 3, 7], name: "Minor"),
      \dim -> Chord.new(notes: [0, 3, 6], name: "Diminished"),
      \aug -> Chord.new(notes: [0, 4, 8], name: "Augmented"),
      \dim7 -> Chord.new(notes: [0, 3, 6, 9], name: "Diminished 7th"),
      \five -> Chord.new(notes: [0, 7], name: "Fifth"),
      \dom7 -> Chord.new(notes: [0, 4, 7, 10], name: "Dominant 7th"),
      \maj7 -> Chord.new(notes: [0, 4, 7, 11], name: "Major 7th"),
      \m7 -> Chord.new(notes: [0, 3, 7, 10], name: "Minor 7th"),
      \mMaj7 -> Chord.new(notes: [0, 3, 7, 11], name: "Minor-Major 7th"),
      \sus4 -> Chord.new(notes: [0, 5, 7], name: "Suspended 4th"),
      \sus2 -> Chord.new(notes: [0, 2, 7], name: "Suspended 2nd"),
      \six -> Chord.new(notes: [0, 4, 7, 9], name: "Six"),
      \m6 -> Chord.new(notes: [0, 3, 7, 9], name: "Minor 6th"),
      \nine -> Chord.new(notes: [0, 4, 7, 10, 14], name: "Nine"),
      \m9 -> Chord.new(notes: [0, 3, 7, 10, 14], name: "Minor 9th"),
      \maj9 -> Chord.new(notes: [0, 4, 7, 11, 14], name: "Major 9th"),
      \mMaj9 -> Chord.new(notes: [0, 3, 7, 11, 14], name: "Minor-Major 9th"),
      \eleven -> Chord.new(notes: [0, 4, 7, 10, 14, 17], name: "Eleven"),
      \m11 -> Chord.new(notes: [0, 3, 7, 10, 14, 17], name: "Minor 11"),
      \maj11 -> Chord.new(notes: [0, 4, 7, 11, 14, 17], name: "Major 11"),
      \mMaj11 -> Chord.new(notes: [0, 3, 7, 11, 14, 17], name: "Minor-Major 11"),
      \thirteen -> Chord.new(notes: [0, 4, 7, 10, 14, 21], name: "Thirtheen"),
      \m13 -> Chord.new(notes: [0, 3, 7, 10, 14, 21], name: "Minor 13"),
      \maj13 -> Chord.new(notes: [0, 4, 7, 11, 14, 21], name: "Major 13"),
      \mMaj13 -> Chord.new(notes: [0, 3, 7, 11, 14, 21], name: "Minor-Major 13"),
      \add9 -> Chord.new(notes: [0, 4, 7, 14], name: "Add 9"),
      \madd9 -> Chord.new(notes: [0, 3, 7, 14], name: "Minor Add 9"),
      \sixadd9 -> Chord.new(notes: [0, 4, 7, 9, 14], name: "Add Six-Nine"),
      \m6add9 -> Chord.new(notes: [0, 3, 7, 9, 14], name: "Minor Six Add Nine"),
      \add11 -> Chord.new(notes: [0, 4, 7, 10, 17], name: "Add Eleven"),
      \majAdd11 -> Chord.new(notes: [0, 4, 7, 11, 17], name: "Major Add Eleven"),
      \mAdd11 -> Chord.new(notes: [0, 3, 7, 10, 17], name: "Minor Add Eleven"),
      \mMajAdd11 -> Chord.new(notes: [0, 3, 7, 11, 17], name: "Minor-Major Add Eleven"),
      \add13 -> Chord.new(notes: [0, 4, 7, 10, 21], name: "Add Thirteen"),
      \majAdd13 -> Chord.new(notes: [0, 4, 7, 11, 21], name: "Major Add 13"),
      \mAdd13 -> Chord.new(notes: [0, 3, 7, 10, 21], name: "Minor Add 13"),
      \mMajAdd13 -> Chord.new(notes: [0, 3, 7, 11, 21], name: "Minor-Major Add 13"),
      \sevenFlat5 -> Chord.new(notes: [0, 4, 6, 10], name: "7 Flat 5"),
      \sevenSharp5 -> Chord.new(notes: [0, 4, 8, 10], name: "7 Sharp 5"),
      \sevenFlat9 -> Chord.new(notes: [0, 4, 7, 10, 13], name: "7 Flat 9"),
      \sevenSharp9 -> Chord.new(notes: [0, 4, 7, 10, 15], name: "7 Sharp 9"),
      \sevenSharp5Flat9 -> Chord.new(notes: [0, 4, 8, 10, 13], name: "7 Sharp 5 Flat 9"),
      \m7Flat5 -> Chord.new(notes: [0, 3, 6, 10], name: "Minor 7 Flat 5"),
      \m7dim -> Chord.new(notes: [0, 3, 6, 9], name: "Minor 7 Diminished"),
      \m7Sharp5 -> Chord.new(notes: [0, 3, 8, 10], name: "Minor 7 Sharp 5"),
      \m7Flat9 -> Chord.new(notes: [0, 3, 7, 10, 13], name: "Minor 7 Flat 9"),
      \nineSharp11 -> Chord.new(notes: [0, 4, 7, 10, 14, 18], name: "9 Sharp 11"),
      \nineFlat13 -> Chord.new(notes: [0, 4, 7, 10, 14, 20], name: "9 Flat 13"),
      \sixSus4 -> Chord.new(notes: [0, 5, 7, 9], name: "6 Suspended 4th"),
      \sevenSus4 -> Chord.new(notes: [0, 5, 7, 10], name: "7 Suspended 4th"),
      \maj7Sus4 -> Chord.new(notes: [0, 5, 7, 11], name: "Major 7 Suspended 4th"),
      \nineSus4 -> Chord.new(notes: [0, 5, 7, 10, 14], name: "9 Suspended 4th"),
      \maj9Sus4 -> Chord.new(notes: [0, 5, 7, 11, 14], name: "Major 9 Suspended 4th")
    ];

    all = all.freezeAsParent;
  }

  *at { |key| ^all.at(key); }

  *names { ^(all.keys.asArray ++ all.parent.keys).sort; }

  *directory {
    ^this.names.collect{ |k| "%: % has % Notes".format(all.at(k).name, k, all.at(k).size) }.join("\n")
  }

  size {
    ^this.notes.size;
  }

  *choose { |size = 16|
    var loop = this.chooseFromSelected {	|x| (x.size == size) };
    if(loop.isNil) {
      "No known chord with size %".format(size).warn
    };
    ^loop
  }

  *chooseFromSelected { |selectFunc|
    selectFunc = selectFunc ? { true };
    ^(all.copy.putAll(all.parent)).select(selectFunc)
    .choose.deepCopy;
  }

  *doesNotUnderstand { |selector, args|
    var chord = this.at(selector, args).deepCopy;
    ^chord ?? { super.doesNotUnderstand(selector, args) };
  }

  storedKey {
    // can be optimised later
    ^all.findKeyForValue(this)
  }

  printOn { |stream| this.storeOn(stream) }
  storeOn { |stream|
    var storedKey = this.storedKey;
    stream << this.class.name;
    if(storedKey.notNil) { stream << "." << storedKey } { this.storeParamsOn(stream) }
  }
  storeArgs { ^[notes, name] }

}