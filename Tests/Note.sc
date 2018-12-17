TestNote : UnitTest {

  test_Semitones {

    this.assertEquals(Note.at(\c), 0, "Note.at(\c) -> 0");
    this.assertEquals(Note.g, 7, "Note.g -> 7");
    this.assertEquals([\c, \a, \f, \e].asNote, [ 0, 9, 5, 4 ], "[\c, \a, \f, \e] -> [ 0, 9, 5, 4 ]");
    this.assertEquals(
      Pseq([\c, \a, \f, \e], inf).asStream.nextN(10),
      [ 0, 9, 5, 4, 0, 9, 5, 4, 0, 9 ],
      "Pseq[\c, \a, \f, \e] -> [ 0, 9, 5, 4, ... ]"
    );

  }

}