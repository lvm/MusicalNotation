/*
Note class

(c) 2018 by Mauro <mauro@sdf.org>
http://www.cyberpunk.net.ar/

A class that returns Chromatic semitones.

*/

Note {
  var <symbol;
  classvar <all;

  *new { |symbol|
    ^super.new.init(symbol);
  }

  init { |inSymbol|
    symbol = inSymbol ?? \r;
    ^this.prSemitone(symbol);
  }

  *initClass {
    all = IdentityDictionary[
      \c -> 0,
      \cs -> 1, \df -> 1,
      \d -> 2,
      \ds -> 3, \ef -> 3,
      \e -> 4,
      \f -> 5,
      \fs -> 6, \gf -> 6,
      \g -> 7,
      \gs -> 8, \af -> 8,
      \a -> 9,
      \as -> 10, \bf -> 10,
      \b -> 11
    ];

    all = all.freezeAsParent;
  }

  *at { |key| ^all.at(key); }

  *names { ^(all.keys.asArray ++ all.parent.keys).sort; }

  *directory {
    ^this.names.collect{ |k| "%: Semitone %".format(k, all.at(k)) }.join("\n")
  }

  prSemitone { |input = \rest|
    // recursive funkyness
    if(input.isKindOf(Collection)) { ^input.collect(this.prSemitone(_)); };
    // return the note or a rest
    if(all.at(input.asSymbol).notNil) {  ^all.at(input.asSymbol);  };
    if(input.isRest) { ^Rest(); };
    // or whatever
    ^input;
  }

  *doesNotUnderstand { |selector, args|
    var note = this.at(selector, args).deepCopy;
    ^note ?? { super.doesNotUnderstand(selector, args) };
  }

  printOn { |stream| this.storeOn(stream) }
  storeOn { |stream| stream << "Note(\\" << symbol << ")"; }
  storeArgs { ^[symbol] }

}