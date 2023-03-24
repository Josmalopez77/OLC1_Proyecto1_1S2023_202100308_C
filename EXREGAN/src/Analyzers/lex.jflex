package Analyzers;
import java_cup.runtime.Symbol; 

import exregan.MasterMindUI.*;
import exregan.MasterMindUI;
import exregan.GUI.*;
import exregan.GUI;
%% 
%class Lexical
%public 
%line 
%column 
%cupsym sym
%cup 
%char
%unicode
%ignorecase

%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 
Digit=[0|1|2|3|4|5|6|7|8|9]+
White=[ \t\r\n]+

Letter=[a-zA-Z_]
Word = ({Letter}+|{Digit}*)+
LettersList = (({Letter}{White}*",")+{White}*|{Letter})+

MultilineComment = "<!""!"*([^!>]|[^!]">"|"!"[^>])*"!"*"!>"
NumbersList=(([0-9]{White}*",")+{White}*|[0-9])+
Character = (.?)
SmallString = (\"(.?)\")

BigString = [\"]([^\"\n]|(\\\"))*[\"]

ExpReg = "{"([a-zA-Z_]|[0-9]+)+"}"
Comment = "//"(.*)
%%

"CONJ" {return new Symbol(sym.conj,yyline,(int)yychar, yytext());}
"," {return new Symbol(sym.comma,yyline,(int)yychar, yytext());}
"?" {return new Symbol(sym.questionMark,yyline,(int)yychar, yytext());} 
"{" {return new Symbol(sym.openingKey,yyline,(int)yychar, yytext());}
"|" {return new Symbol(sym.or,yyline,(int)yychar, yytext());}
"}" {return new Symbol(sym.closingKey,yyline,(int)yychar, yytext());}
"%" {return new Symbol(sym.percent, yyline, (int)yychar, yytext());}
//"%" {return new Symbol(sym.Cierre, yyline, (int)yychar, yytext());}
"*" {return new Symbol(sym.zeroOrMore,yyline,(int)yychar, yytext());} 
"+" {return new Symbol(sym.oneOrMore,yyline,(int)yychar, yytext());}  
"-" {return new Symbol(sym.line,yyline,(int)yychar, yytext());} 
"." {return new Symbol(sym.point,yyline,(int)yychar, yytext());} 
":" {return new Symbol(sym.twoPoints,yyline,(int)yychar, yytext());} 
";" {return new Symbol(sym.semicolon,yyline,(int)yychar, yytext());} 
">" {return new Symbol(sym.greaterThan,yyline,(int)yychar, yytext());} 
"~" {return new Symbol(sym.prime,yyline,(int)yychar, yytext());}
"\\\"" {return new Symbol(sym.doubleQuote, yyline, (int)yychar, yytext());}
"\\\'" {return new Symbol(sym.singleQuote, yyline, (int)yychar, yytext());}
"\\n" {return new Symbol(sym.lineJump, yyline, (int)yychar, yytext());}
\n  {yycolumn=1;}

{White} {/*Se ignoran*/}
{Comment} {/*Se ignoran*/}
{MultilineComment} {/*Se ignoran*/}
{Digit} {return new Symbol(sym.Digit, yycolumn, yyline, yytext());}
{Letter} {return new Symbol(sym.Letter, yycolumn, yyline, yytext());}
{Word} {return new Symbol(sym.Word,yycolumn,yyline,yytext());}
{NumbersList} {return new Symbol(sym.NumbersList,yycolumn,yyline,yytext());}
{LettersList} {return new Symbol(sym.LettersList,yycolumn,yyline,yytext());}
{SmallString} {return new Symbol(sym.SmallString, yycolumn, yyline, yytext());}
{BigString} {return new Symbol(sym.BigString, yycolumn, yyline, yytext());}
{Character} {return new Symbol(sym.Character, yycolumn, yyline, yytext());}
{ExpReg} {return new Symbol(sym.ExpReg,yycolumn,yyline,yytext());}








. {
    System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline)+" Columna:"+(yycolumn));
    MasterMindUI.errorDaoHandler.newError("Tipo Lexico", yytext(), yyline, yycolumn);
    GUI.errorDaoHandler.newError("Tipo Lexico", yytext(), yyline, yycolumn);
  }