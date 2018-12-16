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

  *names { ^all.parent.keys.asArray.sort; }

  *directory {
    ^this.names.collect{ |k| "%: Semitone %".format(k, all.at(k)) }.join("\n")
  }

  *at { |input = \rest|
    if(all.at(input).notNil) { ^all.at(input); };
    if(input.isRest && [\r,\rest,\].indexOf(input).notNil) { ^Rest(); };
    ^input;
  }

  *doesNotUnderstand { |selector, args|
    var note;
    if (selector.class == Symbol) {
      note = this.at(selector, args).deepCopy;
      ^note ?? { super.doesNotUnderstand(selector, args) };
    }
    ^super.doesNotUnderstand(selector, args);
  }

  printOn { |stream| this.storeOn(stream) }
  storeOn { |stream| stream << this.class.name << "(\\" << symbol << ")"; }
  storeArgs { ^[symbol] }



}