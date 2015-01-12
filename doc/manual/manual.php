<?
		require "include/page.php";
		make_header("documentation", "Manual", "./", "@import \"manual.css\";");
		?><h1 xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs">jStyleParser Manual</h1><p xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="author">
			[ <a href="manual.html">Downloadable version</a> ]
		</p><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="toc"><h2>Table of Contents</h2><ul><li><a href="#intro">Introduction</a></li><li><a href="#parsing">Style Sheet Parsing</a><ul><li><a href="#sheet">Parsed style sheet processing</a></li></ul></li><li><a href="#used">Obtaining the style sheets used in an HTML document</a></li><li><a href="#dom">DOM Analysis</a><ul><li><a href="#analyze">Analyzing a Style Sheet</a></li><li><a href="#direct">Simplified and Direct Usage Method</a></li><li><a href="#dom">Retrieving the Style of DOM Elements</a></li><li><a href="#pseudoelements">Obtaining the Style of Pseudo-Elements</a></li><li><a href="#pseudoclasses">Applying Pseudo-Classes</a></li></ul></li><li><a href="#media">Media</a><ul><li><a href="#media_parser">Automatic loading of imported style sheets</a></li><li><a href="#media_dom">DOM style analysis</a></li></ul></li><li><a href="#structure">Internal Structure of the Library</a><ul><li><a href="#package_css">Package cz.vutbr.web.css</a></li><li><a href="#package_csskit">Package cz.vutbr.web.csskit</a></li><li><a href="#package_domassign">Package cz.vutbr.web.domassign</a></li></ul></li><li><a href="#extend">Extending Current Version</a><ul><li><a href="#performance">Extending Performance</a></li></ul></li></ul></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="intro"><h2>Introduction</h2><p>
jStyleParser is a Java library for parsing CSS style sheets and assigning styles to the HTML or XML
document elements according to the W3C CSS 2.1 specification and a subset of the CSS 3 specification.
It allows parsing the individual CSS files as well as computing the efficient style of the DOM elements.  
</p></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="parsing"><h2>Style Sheet Parsing</h2><p>This functionality may be used for parsing individual style sheets obtained from a remote file (URL),
local file or a string. Three static methods are defined in the <a href="api/cz/vutbr/web/css/CSSFactory.html" class="api"><code>CSSFactory</code></a> class for
this purpose:</p><ul>
	<li><a href="api/cz/vutbr/web/css/CSSFactory.html#parse(java.net.URL, java.lang.String)" class="api"><code>StyleSheet parse(URL url, String encoding)</code></a>, the most general method. 
			Transforms data available at the given <code>url</code>, expecting given <code>encoding</code>,
			or taking the default one if it is not provided.,			
	</li>
	<li><a href="api/cz/vutbr/web/css/CSSFactory.html#parse(java.net.URL, cz.vutbr.web.css.NetworkProcessor, java.lang.String)" class="api"><code>StyleSheet parse(URL url, NetworkProcessor network, String encoding)</code></a> 
			that has the same effect as above with providing a custom <code>NetworkProcessor</code> (see below), 			
	</li>
	<li><a href="api/cz/vutbr/web/css/CSSFactory.html#parse(java.lang.String, java.lang.String)" class="api"><code>StyleSheet parse(String fileName, String encoding)</code></a>,
			which internally transforms the
			<code>fileName</code> into an URL and		
	</li>
	<li><a href="api/cz/vutbr/web/css/CSSFactory.html#parseString(java.lang.String, java.net.URL)" class="api"><code>StyleSheet parseString(String css, URL base)</code></a>,
			which can be used to parse embedded CSS declarations that is declarations between the &lt;style&gt; tags.
	</li>
</ul><p>During the parsing process, the parser automatically imports all the style sheets referenced using the <code>@import</code>
rules. See the <a href="#media">Media</a> section for further reference about how to limit this behavior to certain
media only or disable it completely.</p><p>For obtaining the imported style sheets referenced by their URLs, it is possible to provide a custom implementation
of a <a href="api/cz/vutbr/web/css/NetworkProcessor.html" class="api"><code>NetworkProcessor</code></a> that simply provides obtaining an <code>InputStream</code> from the given URL.
The default built-in <code>NetworkProcessor</code> is based on the standard Java <code>URLConnection</code> infrastructure.</p><p>The result of the parsing is a <a href="api/cz/vutbr/web/css/StyleSheet.html" class="api"><code>StyleSheet</code></a> object that is generally a collection
of <em>rules</em> discovered in the style sheet. It can be also used as the input for the DOM <a href="api/cz/vutbr/web/domassign/Analyzer.html" class="api"><code>Analyzer</code></a> as described
below in the <a href="#dom">DOM Analysis</a> section.</p><div class="subsection" id="sheet"><h3>Parsed style sheet processing</h3><p>When the DOM Analyzer feature is not used, the parsed style sheed may be manually traversed in order to obtain the contained
rules and declarations. The most important data structures are the following:</p><ul>
	<li>The <a href="api/cz/vutbr/web/css/StyleSheet.html" class="api"><code>StyleSheet</code></a> is a collection of <em>rules</em>.</li>
	<li>Each rule is an instance of <a href="api/cz/vutbr/web/css/RuleBlock.html" class="api"><code>RuleBlock</code></a>. There exist several
		subclases of this class that correspond to the individual CSS rule types. The most important of them are
		<a href="api/cz/vutbr/web/css/RuleSet.html" class="api"><code>RuleSet</code></a> (a standard CSS rule with a selector and declarations)
		and <a href="api/cz/vutbr/web/css/RuleMedia.html" class="api"><code>RuleMedia</code></a> (the <code>@media</code> rule containing further <code>RuleSet</code> rules).</li>
	<li>The <code>RuleSet</code> is a collection of <a href="api/cz/vutbr/web/css/Declaration.html" class="api"><code>Declaration</code></a> objects.
		The selectors used for the whole rule may be obtained using the 
		<a href="api/cz/vutbr/web/css/RuleSet.html#getSelectors()" class="api"><code>getSelectors()</code></a> method.</li>
	<li>Each <code>Declaration</code> is a set of <a href="api/cz/vutbr/web/css/Term.html" class="api"><code>Term</code></a> objects that represent
		the values that are assigned to the given property. There exist many subclasses of <code>Term</code> that represent
		the individual value types in CSS (lengths, integers, colors, identifiers, etc.) The name of the property can be obtained using its
		<a href="api/cz/vutbr/web/css/Declaration.html#getProperty()" class="api"><code>getProperty()</code></a> method.</li>
</ul><p>A simple example of the style sheet processing is available in the
	<a href="http://github.com/radkovo/jStyleParser/blob/master/src/test/java/test/ParserDemo.java">test.ParserDemo</a> example.
</p></div></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="used"><h2>Obtaining the style sheets used in an HTML document</h2><p>The <a href="api/cz/vutbr/web/css/CSSFactory.html" class="api"><code>CSSFactory</code></a> provides a method that
analyzes an HTML or XML document represented by a DOM and it parses all the referenced style sheets:</p><ul>
<li><a href="api/cz/vutbr/web/css/CSSFactory.html#getUsedStyles(org.w3c.dom.Document, java.lang.String, java.net.URL, cz.vutbr.web.css.MediaSpec)" class="api"><code>StyleSheet CSSFactory.getUsedStyles(Document doc, String encoding, URL base, MediaSpec media)</code></a></li>
</ul><p>It parses all the style sheets referenced from the document that correspond to the specified media
and it returns a single style sheet structure that contains all the relevant CSS rules. The <code>base</code>
URL is used for the possible relative URLs used in the style sheets. The <code>encoding</code> specifies
a default encoding that is used when the encoding is not specified within the style sheet. Finally, <code>media</code>
specifies the current media features that should be used for evaluating the media queries used in the style sheet
as described in the <a href="#media">Media</a> section.
</p></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="dom"><h2>DOM Analysis</h2><p>The purpose of the DOM analysis is to apply the relevant style sheets to a particular HTML or XML document
represented by a DOM in order to obtain the efficient styles of the individual document elements. The result
of the analysis is a mapping between the DOM elements and the corresponding CSS declarations.
This mapping can be used either for displaying the HTML document or for performing some further analysis on
the document structure.</p><div class="subsection" id="analyze"><h3>Analyzing a Style Sheet</h3><p>When a <code>StyleSheet</code> instance is <a href="#parsing">obtained from the parser</a>, it can be passed to an
<a href="api/cz/vutbr/web/domassign/Analyzer.html" class="api"><code>Analyzer</code></a>. 
</p><p>The <code>Analyzer</code> basically provides the following method:</p><ul>
	<li><a href="api/cz/vutbr/web/domassign/Analyzer.html#evaluateDOM(org.w3c.dom.Document, cz.vutbr.web.css.MediaSpec, boolean)" class="api"><code>StyleMap evaluateDOM(Document doc, MediaSpec media,
			boolean inherit)</code></a>, which constructs a map between DOM elements 
			and their CSS declarations according to the given <code>media</code> specification and
			allowed/disabled inheritance of declarations. 
	</li>
</ul><p>Additionally, a
<a href="api/cz/vutbr/web/domassign/DirectAnalyzer.html" class="api"><code>DirectAnalyzer</code></a>
analyzer class is provided for cases when it is not necessary to evaluate the whole DOM. It computes a style for individual DOM nodes without
creating the whole map. It is suitable for obtaining the style of individual elements without computing the style for the whole DOM tree.
However, in larger scale, the performance of the individual computation is significantly worse.</p></div><div class="subsection" id="direct"><h3>Simplified and Direct Usage Method</h3><p>To provide simpler approach while parsing an (X)HTML document, <code>CSSFactory</code>
provides the following method:</p><ul>
	<li><a href="api/cz/vutbr/web/css/CSSFactory.html#assignDOM(org.w3c.dom.Document, java.lang.String, java.net.URL, java.lang.String, boolean)" class="api"><code>StyleMap assignDOM(Document doc, String encoding,
			URL base, MediaSpec media, boolean useInheritance)</code></a></li>
</ul><p>It automatically downloads and parses all the internal and external style sheets referenced from a DOM for the given media and 
runs the style mapping to the given DOM. It creates and assigns a <code>NodeData</code> to each element in the DOM document <code>doc</code> for the 
given medium <code>media</code>. While searching for externally stored CSS style sheets, base URL <code>base</code> is used.</p><p>There exist several variants of the <code>assignDOM()</code> method in the
<a href="api/cz/vutbr/web/css/CSSFactory.html" class="api"><code>CSSFactory</code></a> class that allow to specify
a custom <code>MatchCondition</code> for matching pseudoclasses (as discussed in the
<a href="#pseudoclasses">pseudoclasses section</a>) and/or a custom
<code>NetworkProcessor</code> for obtaining the imported style sheets as discussed
in the <a href="#parsing">parsing section</a>.</p></div><div class="subsection" id="dom"><h3>Retrieving the Style of DOM Elements</h3><p> 
When the analyzing part is done for the style sheet, the computed mapping between DOM elements and
the <a href="api/cz/vutbr/web/css/NodeData.html" class="api"><code>NodeData</code></a> structures representing their styles is available
as a <a href="api/cz/vutbr/web/domassign/StyleMap.html" class="api"><code>StyleMap</code></a> structure. This structure extends the standard Java
<code>Map</code> structure and the style of a particular DOM element may be therefore obtained using the following method:
</p><ul>
	<li><code><b>NodeData get(org.w3c.dom.Element)</b></code></li>
</ul><p>The <a href="api/cz/vutbr/web/css/NodeData.html" class="api"><code>NodeData</code></a> structure provides two basic methods:</p><ul>
	<li><a href="api/cz/vutbr/web/css/NodeData.html#getProperty(java.lang.String, boolean)" class="api"><code>public &lt;T extends CSSProperty&gt; T getProperty(String name,
			boolean includeInherited)</code></a>, which returns a <a href="api/cz/vutbr/web/css/CSSProperty.html" class="api"><code>CSSProperty</code></a>.
		Basically, these properties are the implementations of the <code>CSSProperty</code> by specialized <code>enums</code>.
		To distinguish between constants values and variable values, following contract is used for the enum values:
		<ul>
			<li>UPPERCASE are the constant values and</li>
			<li>lowercase are the values that contain additional information, 
				which can be retrieved by the following function.</li> 
		</ul> 
	</li>
	<li><a href="api/cz/vutbr/web/css/NodeData.html#getValue(java.lang.Class, java.lang.String, boolean)" class="api"><code>public &lt;T extends Term&lt;?&gt;&gt; T getValue(Class&lt;T&gt; clazz, String name,
			boolean includeInherited);</code></a>
		retrieves a value of type <a href="api/cz/vutbr/web/css/Term.html" class="api"><code>Term</code></a>, determined in package <code>cz.vutbr.web.css</code>. 		
	</li>
</ul><p>For both these methods, there are equivalent ones defined with automatic inclusion of inherited properties/terms.</p><p>Example of enum values for the <code>CSSProperty</code> <code>max-height</code>: 
 <code>length</code>, <code>percentage</code>, <code>NONE</code>, <code>INHERIT</code></p><p>
Value <code>INHERIT</code> is present for all properties, <code>length</code> and <code>percentage</code> determine
type of token which is about to be retrieved to get exact information about style
</p><p>
For determining the type of the <code>CSSProperty</code>, compiler inference is used. Strictly speaking
that means that the type of L-value(expression at the left side of equal-sign) is used to determine
type to which the result is casted. This could lead in <code>ClassCastException</code> in cases when
the user uses invalid combination of L-value type and property name.
<br/>
When there is no L-value, the supertype (that is <code>CSSProperty</code>) is used to cast the resulting expression.
This is always valid cast. 
</p><p>The following example shows how to obtain the value of a top margin of an element:</p><div class="code"><pre>
<em>//get the element style</em>
StyleMap map = CSSFactory.assignDOM(doc, encoding, base, medium, true);
NodeData style = map.get(<i>element</i>);
<em>//get the type of the assigned value</em>
CSSProperty.Margin mm = style.getProperty("margin-top");
System.out.println("margin-top=" + mm);
<em>//if a length is specified, obtain the exact value</em>
if (mm == Margin.length)
{
    TermLength mtop = style.getValue(TermLength.class, "margin-top");
    System.out.println("value=" + mtop);
}
</pre></div></div><div class="subsection" id="pseudoelements"><h3>Obtaining the Style of Pseudo-Elements</h3><p>CSS specification allows the use of <a href="http://www.w3.org/TR/CSS21/selector.html#pseudo-element-selectors">several pseudo-elements</a>
for addressing specific parts of the existing DOM elements. The style of the pseudo-elements may be accessed using the following method
of the <a href="api/cz/vutbr/web/domassign/StyleMap.html" class="api"><code>StyleMap</code></a> obtained for the DOM tree:
</p><ul>
	<li><code><b>boolean hasPseudo(org.w3c.dom.Element, Selector.PseudoDeclaration)</b></code> checks whether the given element
	has some style for the particular pseudo-element declared.</li>
	<li><code><b>NodeData get(org.w3c.dom.Element, Selector.PseudoDeclaration)</b></code> obtains the style of the particular 
	pseudo-element of the given DOM element.</li>
</ul><p>The pseudo-elements are specified by a <a href="api/cz/vutbr/web/css/Selector.PseudoDeclaration.html" class="api"><code>Selector.PseudoDeclaration</code></a>
pseudo-element value and may be one of the following: <code>BEFORE</code>, <code>AFTER</code>, <code>FIRST_LETTER</code> or <code>FIRST_LINE</code>.
The remaining values of <a href="api/cz/vutbr/web/css/Selector.PseudoDeclaration.html" class="api"><code>Selector.PseudoDeclaration</code></a> correspond to pseudo-classes
that must be treated differently as described in the following section.
</p></div><div class="subsection" id="pseudoclasses"><h3>Applying Pseudo-Classes</h3><p>jStyle parser supports a subset of the available <a href="http://www.w3.org/TR/selectors/#pseudo-classes">CSS3 pseudo-classes</a>:
the <a href="http://www.w3.org/TR/selectors/#structural-pseudos">structural pseudo-classes</a> and the
<a href="http://www.w3.org/TR/selectors/#dynamic-pseudos">dynamic pseudo-classes</a>.
</p><p>
The <em>structural pseudo-classes</em> (such as <code>:first-child</code>) are supported and evaluated automatically. Their defined
style is automatically included in the resulting style assigned to the appropriate DOM elements in the resulting <code>StyleMap</code>.
</p><p>
The <em>dynamic pseudo-classes</em> (such as <code>:hover</code>) are more complicated. Any element may belong dynamically to several
pseudo-classes that influence the resulting style of the element itself but also the style of its child elements. Therefore,
before the DOM style is evaluated as described in <a href="#analyze">previous</a> <a href="#direct">sections</a>,
the current pseudo-classes must be assigned to the individual elements in order to compute the resulting styles properly. 
</p><p>The default behavior of jStyleParser corresponds to the standard static HTML file displaying behavior:</p><ul>
	<li>Links represented using the <code>&lt;a&gt;</code> tags are assigned the <code>:link</code> pseudo-class.</li>
	<li>No dynamic pseudo-classes are assigned to the remaining elements.</li>
</ul><p>For specifying other pseudo-classes for different elements, a special <a href="api/cz/vutbr/web/css/MatchCondition.html" class="api"><code>MatchCondition</code></a> mechanism 
may be used. Generally, a <a href="api/cz/vutbr/web/css/MatchCondition.html.html" class="api"><code>MatchCondition</code></a> specifies an additional condition
applied when matching specific parts of the CSS selectors. Its default implementation <a href="api/cz/vutbr/web/csskit/MatchConditionImpl.html" class="api"><code>MatchConditionImpl</code></a>
implements the default behavior described above. For implementing a better behavior, a configurable <a href="api/cz/vutbr/web/csskit/MatchConditionOnElements.html" class="api"><code>MatchConditionOnElements</code></a>
implementation is prepared. It allows do assign a set of pseudo-classes directly to given DOM elements or to specified element names. It usage
is demonstrated on the following code:
</p><div class="code"><pre>
<em>//obtain the elements e1 and e2 that should be assigned the style. e.g.:</em>
Element e1 = document.getElementById('element1');
Element e2 = ... <em>//or any other way of obtaining a DOM Element</em>

<em>//Create the match condition. Preserve the default behavior:</em>
<em>//  all &lt;a&gt; links are assigned the :link class</em>
MatchConditionOnElements cond = new MatchConditionOnElements("a", PseudoDeclaration.LINK);

<em>//assign pseudo-classes to the selected elements</em>
cond.addMatch(e1, PseudoDeclaration.HOVER);
cond.addMatch(e2, PseudoDeclaration.VISITED);

<em>//register the match condition so that it is used by jStyleParser</em>
CSSFactory.registerDefaultMatchCondition(cond);

<em>//evaluate the DOM styles as normally</em>
StyleMap decl = CSSFactory.assignDOM(doc, null, base, "screen", true);
...
</pre></div><p>When the pseudo-class assignment changes, the match condition must be reconfigured and the DOM style must be recomputed.</p><p>Alternatively, it is possible to pass the <code>MatchCondition</code> directly to the DOM analyzer instead of using the global
<code>registerDefaultMatchCondition()</code> function. When the Analyzer is used for evaluating the DOM
(<a href="#analyze">as discussed here</a>), the match condition may be registered using its
<a href="api/cz/vutbr/web/domassign/Analyzer.html#registerMatchCondition(cz.vutbr.web.css.MatchCondition)" class="api"><code>registerMatchCondition</code></a> function.
When using the <a href="#direct">similified method</a> (using CSSFactory), the match condition may be passed as an additional
argument to the <a href="api/cz/vutbr/web/css/CSSFactory.html#assignDOM(org.w3c.dom.Document, java.lang.String, java.net.URL, cz.vutbr.web.css.MediaSpec, boolean, cz.vutbr.web.css.MatchCondition)" class="api"><code>CSSFactory.assignDOM()</code></a> method.</p></div></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="media"><h2>Media</h2><p>The style sheets are typically evaluated for some particular media. In jStyleParser the type and the features of the media
being used are specified using the <a href="api/cz/vutbr/web/css/MediaSpec.html" class="api"><code>MediaSpec</code></a> structure. It specifies the media type
such as "screen" or "print" and the features such as display area size, device size, colors, etc. A simple media specification
may be created as follows:</p><div class="code"><pre>
<em>//create a 'screen' media type with default feature values</em>
MediaSpec media = new MediaSpec("screen");

<em>//set the display area size in pixeld</em>
media.setDimensions(1600, 1200);
</pre></div><p>The created media specification may be used for several parsing and analysis steps as described in the following sections.</p><div class="subsection" id="media_parser"><h3>Automatic loading of imported style sheets</h3><p>By default, the parser automatically loads and recursively parses <b>all</b> the style sheets imported using 
the <code>@import</code> rules. The optional media queries used in the <code>@import</code> rules are evaluated later during the 
DOM analysis. However, the automatic download may be limited to certain media only by using the
 <a href="api/cz/vutbr/web/css/CSSFactory.html.html#setAutoImportMedia(cz.vutbr.web.css.MediaSpec)" class="api"><code>setAutoImportMedia(cz.vutbr.web.css.MediaSpec media)</code></a>
method. The following code shows several typical configuration.</p><div class="code"><pre>
<em>//import only the style sheets valid for "screen" media type with default values of all the features</em>
CSSFactory.setAutoImportMedia(new MediaSpec("screen"));

<em>//import all the style sheets valid for "screen" media type regardless on the feature values</em>
CSSFactory.setAutoImportMedia(new MediaSpecType("screen"));

<em>//import all style sheets (the default behavior)</em>
CSSFactory.setAutoImportMedia(new MediaSpecAll());

<em>//do not import any style sheets automatically</em>
CSSFactory.setAutoImportMedia(new MediaSpecNone());
</pre></div><p>Note that this specification only affects the behavior of the <code>@import</code> rules during parsing. The media used
for assigning the style to the DOM elements are specified in the further step.</p></div><div class="subsection" id="media_dom"><h3>DOM style analysis</h3><p>The media specification is used in the following steps of the DOM analysis:</p><ul>
<li>When obtaining all style sheets referenced from the HTML or XML code using the
	<a href="api/cz/vutbr/web/css/CSSFactory.html#getUsedStyles(org.w3c.dom.Document, java.lang.String, java.net.URL, cz.vutbr.web.css.MediaSpec)" class="api"><code>StyleSheet CSSFactory.getUsedStyles(Document doc, String encoding, URL base, MediaSpec media)</code></a>
	method. Only the style sheets that match the given media specification are considered.</li>
<li>When assigning the style to the individual DOM elements using the
    <a href="api/cz/vutbr/web/domassign/Analyzer.html#evaluateDOM(org.w3c.dom.Document, cz.vutbr.web.css.MediaSpec, boolean)" class="api"><code>StyleMap evaluateDOM(Document doc, MediaSpec media, boolean inherit)</code></a>
    method. Only the rules that apply to the given media are considered. This includes both the internal <code>@media</code> rules
    and the rules imported using an <code>@import</code> with an optional media query used.</li>
<li>In the direct usage using the
	<a href="api/cz/vutbr/web/css/CSSFactory.html#assignDOM(org.w3c.dom.Document, java.lang.String, java.net.URL, java.lang.String, boolean)" class="api"><code>StyleMap assignDOM(Document doc, String encoding,
			URL base, MediaSpec media, boolean useInheritance)</code></a> method that combines the two cases above together.</li>
</ul></div><p>The default behavior of the parser is to download all the imported style sheets and to apply the media during the DOM analysis. However,
the above methods allow to optimize this process by downloading only the style sheets that are actually used.</p></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="structure"><h2>Internal Structure of the Library</h2><p>The code is divided into following packages:</p><ol>
	<li><code>cz.vutbr.web.css</code>,</li>
	<li><code>cz.vutbr.web.csskit</code>,</li>
	<li><code>cz.vutbr.web.domassign</code> and</li>
	<li><code>test</code>.</li>
</ol><p>
The first package provides in general the contracts used in other applications,
the second one contains its implementation. The third package is about assigning
CSS rules to HTML elements. The last one contains test units.
</p><div class="subsection" id="package_css"><h3>Package cz.vutbr.web.css</h3><p>
In addition to several implementation interfaces, this package provides a general entry
point of the jStyleParser library. It is the <code>CSSFactory</code> class.
By default, the interfaces use an implementation provided by <code>cz.vutbr.csskit</code>
package.
</p><p>
Another remarkable class in this package is the <code>CSSProperty</code> interface, which provides 
a base for CSS properties. By implementing this interface, new CSS properties can be added.
</p></div><div class="subsection" id="package_csskit"><h3>Package cz.vutbr.web.csskit</h3><p>
This package provides a default implementation of <code>cz.vutbr.web.css</code>. This can be changed by
registering other implementation by calling the appropriate methods of <code>CSSFactory</code>. 
</p><p>
Internally, it uses <a href="http://www.antlr.org">ANTLR</a> to parse CSS input into structures 
defined by contracts in the package <code>cz.vutbr.web.css</code>.
</p></div><div class="subsection" id="package_domassign"><h3>Package cz.vutbr.web.domassign</h3><p>
This package provides among others an <a href="api/cz/vutbr/web/domassign/Analyzer.html" class="api"><code>Analyzer</code></a> class,
which is able sort the CSS declarations, to classify them according to a CSS medium and
finally, to assign them to the DOM elements.
</p><p>
The transformation core is implemented in the <code>DeclarationTransformer</code> class.
</p></div></div><div xmlns="http://www.w3.org/1999/xhtml" xmlns:doc="http://cssbox.sourceforge.net/docs" class="section" id="extend"><h2>Extending Current Version</h2><p>An extra work was done to simplify the implementation of new CSS standards.
The implementation is defined by interface contracts.
To implement another CSS parsing level, additional work must be done:</p><ul>
	<li><code>SupportedCSS</code>, which determines the names of the supported CSS properties,
	and their default values must be replaced with a new implementation. See current
	implementation in <code>cz.vutbr.web.domassign.SupportedCSS21</code> for details.
	The new implementation must be registered in the CSSFactory.</li>
	<li>If new CSS properties were added, their implementations of <code>CSSProperty</code>
	must be added, preferably by enum. <code>DeclarationTransformer</code> must be then informed
	that there are new properties and their conversion methods must be written.
	<br/>
	Please note that in current implementation, there are conversion methods defined 
	for all visual CSS 2.1 properties, but they are missing for aural ones, even if
	<code>CSSProperty</code> implementations  for aural properties are well defined.   
	</li>
	<li>
	If the syntax of CSS significantly changes, grammar files <code>CSS.g</code> and 
	<code>CSSTreeParser.g</code> should be rewritten. In current version, they are written
	in a way that should simplify future migration to the grammar of CSS 3.0 (as seen at specification draft).
	Then new parser should be generated. 
	</li>
	<li>If any changes are done in CSS selectors, the <code>Analyzer</code> must be made aware 
	of these changes for the classification of DOM elements according to their selectors.
	</li>
</ul><div class="subsection" id="performance"><h3>Extending Performance</h3><p>During the implementation, some additional storage methods have been tested for
<code>NodeData</code> storage considering time and spatial complexity. Current implementation is
a compromise, specialized to lower memory usage.
All parts of the library can be changed to use a different implementation by changing the 
<code>CSSFactory</code>. Other factories that can be replaced are factory for
creating terms (<code>TermFactory</code>) and factory for creating CSS rule parts
(<code>RuleFactory</code>).</p></div></div><?
		make_footer();
		?>