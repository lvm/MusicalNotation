TestChord : UnitTest {

  test_Chords {

    this.assertEquals(Chord.at(\maj), Chord([ 0, 4, 7 ], "Major"), "Major Chord");
    this.assertEquals(Chord.min, Chord([ 0, 3, 7 ], "Minor"), "Minor Chord");
    this.assertEquals(
      [\maj, \min, \m7].asChord,
      [ Chord([ 0, 4, 7 ], "Major"), Chord([ 0, 3, 7 ], "Minor"), Chord([ 0, 3, 7, 10 ], "Minor 7th") ],
      "Major, Minor, Minor 7th Chords"
    );
    this.assertEquals(
      Pseq([\maj, \min, \m7], inf).asStream.nextN(10),
      [ [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 3, 7, 10 ], [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 3, 7, 10 ], [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 3, 7, 10 ], [ 0, 4, 7 ] ],
      "Pseq[\maj, \min, \m7] -> [ [ 0, 4, 7 ], [ 0, 3, 7 ], [ 0, 3, 7, 10 ], ... ]"
    );

  }

}