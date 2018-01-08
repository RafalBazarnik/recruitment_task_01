*Project:
Automated tests for Xsolve recruitment task

config.properties files needs to have password and username added

project now uses Firefox driver so it require gecko driver

TODO: screenshots, logging, other drivers, more tests, remove unused code, better readme :)

FOUND ISSUES: 
1) option "no magic urls" in new topic creation doesn't seem to work - both with option checked and unchecked urls are parsed and active
2) formatting option [*] asterix has unclear purpose - with bbcode turned on and off - it is just it's code
3) info about config like "[img] is ON" does not change on using checkbox - so it is either unrelated to current post editing (global config) or it isn't working as it is expected
4) Ordered List button/code - [list=][/list] (identical as other list button result) - unordered list!

5) there seems to be some problem occasionaly appearing in loading drafts and submmiting them - "invalid form" validation message apears - I was unable to recreate this validation error by hand - https://ibb.co/gr6Qbm 