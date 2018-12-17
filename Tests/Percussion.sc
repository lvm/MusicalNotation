TestPercussion : UnitTest {

  test_MIDINote {

    this.assertEquals(Percussion.at(\bd), 36, "Percussion.at(\bd) -> 36");
    this.assertEquals(Percussion.bd, 36, "Percussion.bd -> 36");
    this.assertEquals([\bd,\sn].asPercussion, [36, 38], "[\bd,\sn] -> [36, 38]");
    this.assertEquals(
      Pseq([\bd,\sn,\ch,\oh], inf).asStream.nextN(10),
      [ 36, 38, 42, 46, 36, 38, 42, 46, 36, 38 ],
      "Pseq[\bd,\sn,\ch,\oh] -> [ 36, 38, 42, 46, ...]"
    );
  }

}