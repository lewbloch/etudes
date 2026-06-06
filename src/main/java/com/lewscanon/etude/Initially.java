package com.lewscanon.etude;

public class Initially {
 private static final String FMT = "\"%s\": {\"ini\": %d, \"ins\": \"%s\"}";
 private static final String FMT_NULL = "\"%s\": {\"ini\": %d, ins: %s}";

 private int ini;
 private String ins;

 public Initially(int ini, String ins) {
  setIni(ini);
  if (ins == null) {
   return;
  }
  setIns(ins);
 }

 public Initially(int ini) {
  if (ini == 0) {
   return;
  }
  setIni(ini);
 }

 public Initially() {
 }

 @Override
 public String toString() {
  String ins = getIns();
  return String.format(ins == null? FMT_NULL : FMT,
   getClass().getSimpleName(), getIni(), ins);
 }

 final
 public int getIni() {
  return ini;
 }

 public void setIni(int ini) {
  this.ini = ini;
 }

 final
 public String getIns() {
  return ins;
 }

 public void setIns(String ins) {
  this.ins = ins;
 }

 public static void main(String... args) {
  final String FORM = "%s -> %s\n";

  System.out.printf(FORM, "new Initially()", new Initially());
  System.out.printf(FORM, "new Initially(0)", new Initially(0));
  System.out.printf(FORM, "new Initially(1)", new Initially(1));
  System.out.printf(FORM, "new Initially(0, null)", new Initially(0, null));
  System.out.printf(FORM, "new Initially(0, \"null\")", new Initially(0, "null"));
  System.out.printf(FORM, "new Initially(1, null)", new Initially(1, null));
  System.out.printf(FORM, "new Initially(1, \"null\")", new Initially(1, "null"));
  System.out.printf(FORM, "new Initially(0, \"\")", new Initially(0, ""));
  System.out.printf(FORM, "new Initially(17, \"\")", new Initially(17, ""));
 }
}
