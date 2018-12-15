/*
Percussion class
.asPercussion method

(c) 2018 by Mauro <mauro@sdf.org>
http://www.cyberpunk.net.ar/

A class that returns associated MIDI notes according to General MIDI Level 2.
Reference:
* https://www.midi.org/specifications/item/gm-level-1-sound-set
* https://en.wikipedia.org/wiki/General_MIDI_Level_2#Drum_sounds

*/

Percussion {
  var <symbol;
  classvar <all;

  *new { |symbol|
    ^super.new.init(symbol);
  }

  init { |inSymbol|
    symbol = inSymbol ?? \r;
    ^this.prPercussion(symbol);
  }

  *initClass {
    all = IdentityDictionary[
      \hq -> 27, // high q
      \sl -> 28, // slap
      \scr -> 29, // scratch
      \sps -> 29, // scratch push
      \spl -> 30, // scratch pull
      \st -> 31, // sticks
      \sqc -> 32, // sq click
      \mcl -> 33, // metronome click
      \mb -> 34, // metronome bell
      \abd -> 35, // acoustic bass drum
      \bd -> 36, // bass drum
      \sti -> 37, // side stick
      \rm -> 37, // rimshot
      \sn -> 38, // snare
      \cp -> 39, // clap
      \esn -> 40, // electric snare
      \lft -> 41, // low floor tom
      \ch -> 42, // closed hi hat
      \hft -> 43, // high foor tom
      \hh -> 44, // pedal hi hat
      \ph -> 44, // pedal hi hat
      \lt -> 45, // low tom
      \oh -> 46, // open hi-hat
      \lmt -> 47, // low mid tom
      \hmt -> 48, // high mid tom
      \cr -> 49, // crash cymbal
      \ht -> 50, // high tom
      \ri -> 51, // ride cymbal
      \cy -> 52, // chinese cymbal
      \be -> 53, // ride bell
      \ta -> 54, // tambourine
      \scy -> 55, // splash cymbal
      \cow -> 56, // cowbell
      \cr2 -> 57, // crash cymbal 2
      \vib -> 58, // vibraslap
      \ri2 -> 59, // ride cymbal 2
      \hb -> 60, // hi bongo
      \lb -> 61, // low bongo
      \hc -> 62, // hi conga
      \mhc -> 62, // mute hi conga
      \ohc -> 63, // open hi conga
      \lc -> 64, // low conga
      \hti -> 65, // hi timbale
      \lti -> 66, // low timbale
      \hag -> 67, // high agogo
      \lag -> 68, // low agogo
      \ag -> 68, // agogo
      \ca -> 69, // cabasa
      \ma -> 70, // maracas
      \whi -> 71, // whistle
      \swi -> 71, // short whistle
      \lwi -> 72, // long whistle
      \gui -> 73, // guiro
      \sgui -> 73, // short guiro
      \lgui -> 74, // long guiro
      \cl -> 75, // claves
      \hwb -> 76, // hi wood block
      \lwb -> 77, // low wood block
      \mc -> 78, // mute cuica
      \oc -> 79, // open cuica
      \mt -> 80, // mute triangle
      \ot -> 81, // open triangle
      \sha -> 82, //shaker
      \jb -> 83, // jingle bell
      \bt -> 84, // belltree
      \cas -> 85, // castanets
      \ms -> 86, // mute surdo
      \os -> 87, // open surdo
    ];

    all = all.freezeAsParent;
  }

  *at { |key| ^all.at(key); }

  *names { ^(all.keys.asArray ++ all.parent.keys).sort; }

  *directory {
    ^this.names.collect{ |k| "%: MIDI Note %".format(k, all.at(k)) }.join("\n")
  }

  prPercussion { |input = \rest|
    // recursive funkyness
    if(input.isKindOf(Collection)) { ^input.collect(this.prPercussion(_)); };
    // return the note or a rest
    if(all.at(input.asSymbol).notNil) {  ^all.at(input.asSymbol);  };
    if(input.isRest) { ^Rest(); };
    // or whatever
    ^input;
  }

  *doesNotUnderstand { |selector, args|
    var percussion = this.at(selector, args).deepCopy;
    ^percussion ?? { super.doesNotUnderstand(selector, args) };
  }

  printOn { |stream| this.storeOn(stream) }
  storeOn { |stream| stream << "Percussion(\\" << symbol << ")"; }
  storeArgs { ^[symbol] }

}