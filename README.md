# S-conditionGenTool
This is a tool with UIs for generating GEAS's s-conditions automatically. It can be fed with an XML file for raw consistency constraints (rules.xml) and returned a new XML file for consistency constraints with annotated s-conditions for each.

# Preparing steps
	Need rules.xml and patterns.xml with the formation like which is in demo or test folder.
	rules.xml: saving designed FOL consistency constraints
	patterns.xml: saving deployed context change types considered for each context in generating s-conditions

# Using steps:
(1) select suitable rule and pattern files: we give demo and test examples in format in resource/demo resource/test.
	
(2) click "Verify rules" in "Editor" panel to verify the formation of rules

(3) click "Analyze rules" in "Editor" panel to analyze rules and obtain s-conditions for each rule

(4) click "Save to files" in "Editor" panel to save files (rules with annotated s-conditions) for GEAS's usage
