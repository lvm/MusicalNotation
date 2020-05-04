/*
external Methods for Percussion, Note, Chord, Progression classes.

(c) 2018 by Mauro <mauro@sdf.org>
http://www.cyberpunk.net.ar/
*/


// Note.sc
+ SimpleNumber {
  asNote { ^Note.all.parent.findKeyForValue(this).asSymbol; }
  asPercussion { ^Percussion.all.parent.findKeyForValue(this).asSymbol; }
  asNoteMIDI { |octave=5| ^this + (12*octave); }
  asMusicalNotation { ^this; }
  asFreq { |octave=5| ^this.midi(octave).midicps; }
  asCircleOf5th { ^(this + ((0..11) * 7 % 12)); }
}

// Percussion, Note, Chord, Progression
+ Symbol {

  asMusicalNotation {
    var tests = [
      (type: \note, result: this.asNote),
      (type: \chord, result: this.asChord),
      (type: \percussion, result: this.asPercussion)
    ]
    .reject{ |t| t.result.isNil }
    .pop
    ;
    ^(tests.notNil).if {
      (tests.type == \chord).if { tests.result.notes } { tests.result }
    } { this };
  }

  asNote { ^Note.at(this); }
  asChord { ^Chord.at(this); }
  asPercussion { ^Percussion.at(this); }

  embedInStream { ^this.asMusicalNotation.yield; }
}

+ String {
  asNote { ^this.asSymbol.asNote; }
  asChord { ^this.asSymbol.asChord; }
  asPercussion { ^this.asSymbol.asPercussion; }
  asMusicalNotation { ^this.asSymbol.asMusicalNotation; }
}

+ Rest {
  asMusicalNotation { ^this; }
}

+ Collection {
  asNote { ^this.collect(Note.at(_)); }
  asChord { ^this.collect(Chord.at(_)); }
  asPercussion { ^this.collect(Percussion.at(_)); }
  asMusicalNotation {
    ^this.collect{ |item|
      (item.isRest.not).if
      { (item.isKindOf(Meta_Pattern).not).if 
        { item.asMusicalNotation }
        { item }
      }
      { item }
    };
  }

  embedInStream { ^this.collect(_.asMusicalNotation).yield; }
}


+ Scale {
  chordProgression { |name = \sad, key = \c|

    var chords = [];
    var root = Note.at(key);
    var progression = Progression.at(name).degrees;
    var major = [\maj, \min, \min, \maj, \maj, \min, \dim];
    var minor = [\min, \dim, \maj, \min, \min, \maj, \maj];
    var harMinor = [\min, \dim, \aug, \min, \maj, \maj, \dim];
    var melMinor = [\min, \min, \aug, \maj, \maj, \dim, \dim];
    var dorian = [\min, \min, \maj, \maj, \min, \dim, \maj];
    var phrygian = [\min, \maj, \maj, \min, \dim, \maj, \min];
    var lydian = [\maj, \maj, \min, \dim, \maj, \min, \min];
    var mixolydian = [\maj, \min, \dim, \maj, \min, \min, \maj];
    var locrian = [\dim, \maj, \min, \min, \maj, \maj, \min];

    if (this.name == "Major") { chords = major; };
    if (this.name == "Natural Minor") { chords = minor; };
    if (this.name == "Harmonic Minor") { chords = harMinor; };
    if (this.name == "Melodic Minor") { chords = melMinor; };
    if (this.name == "Dorian") { chords = dorian; };
    if (this.name == "Phrygian") { chords = phrygian; };
    if (this.name == "Lydian") { chords = lydian; };
    if (this.name == "Mixolydian") { chords = mixolydian; };
    if (this.name == "Locrian") { chords = locrian; };

    chords = (this.degrees + chords.collect{|n| Chord.at(n).notes;});
    ^Array.fill(progression.size, { |i| chords[progression[i]] }) + root;
  }
}
