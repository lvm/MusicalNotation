/*
Progression class

(c) 2018 by Mauro <mauro@sdf.org>
http://www.cyberpunk.net.ar/

Similar to Scales / Tuning classes but for Progressions.

*/

Progression {
  var <degrees, <>name;
  classvar <all;

  *new { |degrees, name = "Unknown progression"|
    ^super.new.init(degrees, name);
  }
  init { |inDegrees, inName|
    degrees = inDegrees;
    name = inName ?? "Unknown progression";
    ^this;
  }

  *initClass {
    all = IdentityDictionary[
      \sad -> Progression.new(degrees: [0,3,4,4], name: "Sad"),
      \ballad -> Progression.new(degrees: [0,0,3,5], name: "Ballad"),
      \balladAlt -> Progression.new(degrees: [0,3,5,4], name: "Ballad Alt"),
      \rock -> Progression.new(degrees: [0,3,4], name: "Rock"),
      \rockAlt -> Progression.new(degrees: [0,3,0,4], name: "Rock Alt"),
      \roll -> Progression.new(degrees: [0,3,4,3], name: "Roll"),
      \rebel -> Progression.new(degrees: [3,4,3], name: "Rebel"),
      \nrg -> Progression.new(degrees: [0,2,3,5], name: "Energetic"),
      \fifties -> Progression.new(degrees: [0,5,3,4], name: "50s"),
      \creepy -> Progression.new(degrees: [0,5,3,4], name: "Creepy"),
      \creepyAlt -> Progression.new(degrees: [0,5,1,4], name: "Creepy Alt"),
      \gral -> Progression.new(degrees: [0,3,4,0], name: "General"),
      \gralAlt -> Progression.new(degrees: [0,3,1,4], name: "General Alt"),
      \pop -> Progression.new(degrees: [0,4,5,3], name: "Pop"),
      \unresolved -> Progression.new(degrees: [3,0,3,4], name: "Unresolved"),
      \blues -> Progression.new(degrees: [0,3,0,4,0], name: "Blues"),
      \bluesAlt -> Progression.new(degrees: [0,0,0,0,3,3,0,0,4,3,0,0,], name: "Blues Alt"),
      // meh...
      \eleven -> Progression.new(degrees: [1,0,1,4], name: "Eleven"),
      \elevenb -> Progression.new(degrees: [1,4,1,0], name: "Eleven B"),
      \elevenc -> Progression.new(degrees: [1,4,0], name: "Eleven C"),
      \elevend -> Progression.new(degrees: [5,1,4,0], name: "Eleven D"),
    ];

    all = all.freezeAsParent;
  }

  == { arg that;
    ^this.compareObject(that, #[\degrees])
  }

  hash {
    ^this.instVarHash(#[\degrees])
  }

  *at { |key| ^all.at(key); }

  *names { ^(all.keys.asArray ++ all.parent.keys).sort; }

  *directory {
    ^this.names.collect{ |k| "%: % %".format(all.at(k).name, k, all.at(k).roman) }.join("\n")
  }

  size {
    ^this.degrees.size;
  }

  roman {
    ^this.degrees.collect(_.asRomanNumber).join("-");
  }

  *choose { |size = 16|
    var loop = this.chooseFromSelected {	|x| (x.size == size) };
    if(loop.isNil) {
      "No known progression with size %".format(size).warn
    };
    ^loop
  }

  *chooseFromSelected { |selectFunc|
    selectFunc = selectFunc ? { true };
    ^(all.copy.putAll(all.parent)).select(selectFunc)
    .choose.deepCopy;
  }

  *doesNotUnderstand { |selector, args|
    var progression = this.at(selector, args).deepCopy;
    ^progression ?? { super.doesNotUnderstand(selector, args) };
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
  storeArgs { ^[degrees, name] }

}

+ SimpleNumber {
  asRomanNumber {
    var roman = [\i, \ii, \iii, \iv, \v, \vi, \vii, \viii, \ix, \x];
    ^roman.at(this.clip(0,9));
  }
}