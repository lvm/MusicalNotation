TestProgression : UnitTest {

  test_Progs {

    this.assertEquals(
      Progression.at(\sad),
      Progression([ 0, 3, 4, 4 ], "Sad"),
      "'Sad' chord prog"
    );

    this.assertEquals(
      Progression.sad,
      Progression([ 0, 3, 4, 4 ], "Sad"),
      "'Sad' chord prog (method)"
    );

    this.assertEquals(
      Progression.new(degrees: [2, 3, 1, 5], name: "Weird"),
      Progression([ 2, 3, 1, 5 ], "Weird"),
      "Creating a new Progression"
    );

    this.assertEquals(
      Progression.sad.roman,
      "i-iv-v-v",
      "Roman numbers for sad prog"
    );

    this.assertEquals(
      Scale.major.chordProgression(\sad, \c),
      [ [ 0, 4, 7 ], [ 5, 9, 12 ], [ 7, 11, 14 ], [ 7, 11, 14 ] ],
      "Chords for Sad Prog in a Major Scale"
    );

    this.assertEquals(Scale.major.chordProgression(\sad, \g),
      [ [ 7, 11, 14 ], [ 12, 16, 19 ], [ 14, 18, 21 ], [ 14, 18, 21 ] ],
      "Chords for Sad Prog in a Major Scale in a G key"
    );

  }

}