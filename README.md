jStyleParser
============

[![Build Status](https://travis-ci.org/radkovo/jStyleParser.png)](https://travis-ci.org/radkovo/jStyleParser)

jStyleParser is a Java library for parsing CSS style sheets and assigning styles to the HTML or XML document elements according to the CSS 3 specifications.
It allows parsing the individual CSS files as well as computing the efficient style of the DOM elements.

See the project page for more information:
[http://cssbox.sourceforge.net/jstyleparser](http://cssbox.sourceforge.net/jstyleparser)

Installation
------------
With Maven, use the following dependency:
```xml
<dependency>
    <groupId>net.sf.cssbox</groupId>
    <artifactId>jstyleparser</artifactId>
    <version>3.3</version>
</dependency>
```

Parsing CSS
-----------
The basic `CSSFactory` interface provides functions parsing CSS strings, files or URLs. The parsed style sheet
is represented by the corresponding data structures that allow accessing all parts of the style sheet
in a type-safe way.
```java
String css = "div { background-color: red; width: 12px; }";

//parse the style sheet
StyleSheet sheet = CSSFactory.parseString(css, new URL("http://base.url"));

//access the rules and declarations
RuleSet rule = (RuleSet) sheet.get(0);       //get the first rule
CombinedSelector selector = rule.getSelectors()[0]; //read the 'div' selector
Declaration bgDecl = rule.get(0);            //read the 'background-color' declaration
String bgProperty = bgDecl.getProperty();    //read the property name
TermColor color = (TermColor) bgDecl.get(0); //read the color

//print the results
System.out.println(selector);   //prints 'div'
System.out.println(bgProperty); //prints 'background-color'
System.out.println(color);      //prints '#ff0000'

//or even print the entire style sheet (formatted)
System.out.println(sheet);
```

See the details in the [documentation](http://cssbox.sourceforge.net/jstyleparser/manual.php#parsing).

Computing style for DOM elements
--------------------------------
jStyleParser allows to map the style rules to the individual elements in a DOM tree based on their selectors. This allows
obtaining the exact style for any HTML element.

```java
org.w3c.dom.Document doc = ... //source DOM

MediaSpec media = new MediaSpecAll(); //use styles for all media

//create the style map
StyleMap map = CSSFactory.assignDOM(doc, "UTF-8", new URL("https://base.url/"), media, true);

//get the style of a single element
Element div = doc.getElementById("searchelement"); //choose a DOM element
NodeData style = map.get(div); //get the style map for the element
//get the type of the assigned value
CSSProperty.Margin mm = style.getProperty("margin-top");
System.out.println("margin-top=" + mm);
//if a length is specified, obtain the exact value
if (mm == Margin.length)
{
    TermLength mtop = style.getValue(TermLength.class, "margin-top");
    System.out.println("value=" + mtop);
}

```
See the details in the [documentation](http://cssbox.sourceforge.net/jstyleparser/manual.php#dom).


License
-------

All the source code of jStyleParser itself is licensed under the GNU Lesser General
Public License (LGPL), version 3. A copy of the LGPL can be found 
in the LICENSE file.
