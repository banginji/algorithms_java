package com.abc.algorithms.leetcode.string;

import java.util.*;

public class _3557_MaxSubStrings {
    // Model: interval scheduling problems
    // generate all substrings by iterating over the string and then maintain
    // the last index to make sure that non overlapping sub strings are selected
    // abcadrfr
    // lastIdx = -1
    // abca; lastIdx = 3
    // rfr; lastIdx = 7
    public int maxSubstrings(String word) {
        char[] chars = word.toCharArray();
        List<int[]> ranges = new ArrayList<>();
        for (int idx = 0; idx < chars.length; idx++) {
            for (int jdx = idx + 3; jdx < chars.length; jdx++) {
                if (chars[idx] == chars[jdx]) ranges.add(new int[]{idx, jdx});
            }
        }

        ranges.sort((a, b) -> a[1] - b[1]);

        int lastIdx = Integer.MIN_VALUE;
        int count = 0;
        for (int[] range : ranges) {
            if (range[0] > lastIdx) {
                count++;
                lastIdx = range[1];
            }
        }

        return count;
    }

    // The idea is that once a duplicate char occurrence is detected then it is noted
    // and the previous index array is reset and this is repeated during the one iteration
    public int maxSubstringsEff(String word) {
        char[] chars = word.toCharArray();
        int count = 0;

        int[] prevIndices = new int[26];
        Arrays.fill(prevIndices, -1);

        for (int idx = 0; idx < chars.length; idx++) {
            int prevIdx = prevIndices[chars[idx] - 'a'];
            if (prevIdx != -1 && idx - prevIdx + 1 >= 4) {
                count++;
                Arrays.fill(prevIndices, -1);
            } else if (prevIdx == -1) {
                prevIndices[chars[idx] - 'a'] = idx;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(
                new _3557_MaxSubStrings().maxSubstrings("abcdeafdef") == 2
        );

        System.out.println(
                new _3557_MaxSubStrings().maxSubstrings("bcdaaaab") == 1
        );

        System.out.println(
                new _3557_MaxSubStrings().maxSubstrings("aaaeaaa") == 1
        );

        System.out.println(
                new _3557_MaxSubStrings().maxSubstrings("abcceaddba") == 1
        );

        System.out.println(
                new _3557_MaxSubStrings().maxSubstringsEff(
                        "abdbdeccacbbdaeeaeceaacdaaddbcbeedabaeebeecbbeeeeeadecdebbdbaaeacadacdcdcaeeceeabdbddaaeebaeebedaedaadcadcceabbcadceaddeceaebcbeecaebcbcecceaecdbdeceaecddedcbccacbceabbacdbdceebbaaadeebaababdcdeccbacdbeebdceececcbaecbbccdddecacabdbdcbdbadadcaabbeabebceaadbddeebacddaebaaedbcbdbdeebdaeadbdaeaeaddeebdecbebdbbbecceddaddaecbbcbbcdacebbddeeecbcdecddbbcbbbadbcaaddadcdbadceddbacdebdcbcdcadddaabcbedddbbdadacabbedecccdaadaeabedbaacaddadcdcdaacbaeeddcbcbcccdcbaeeecacbcbacdeabacddaadeaccedaedecbdbddcedbcddeaeacddcaacaecdacbbacececeddadcddecadebeeceabdccdbcbddcbbdaecabbbcaedacbbbddeeaadddcbccadaeecceabbabddcadececcabacaabdaedebcecacbbcbacebcabdecebdcdbdacdaedabebbecabecbccecaadadbcdcaedadccdeddbbccaceadbdddddbecaaeedaddbbdeaceeaecbeebebbcbdadcebbcdeaceedeabddcebdebbccabeeebeeeeacacdbcdcbcddcddecdeacadabcbbebcccaaaebbaeaddbbeeebcbaadeaaeabbaaddacecbcdcceddbebcebcdcdaaecacddcbecbcdbceccabeeabccaecdabbaceeeeecbcbaebadbeedcccaddbebbbdcbcadaabdeebebcdbecacddedcededdcdeabebabccbceacabccbaaaaebadbecaeeaddeabaaaebacdedecbeadedbcaebbbaadabcddaeddbbccebbedbeadcdcabbceddedeeacededbeebedbbdbbeacdebdabdcaddaedeaebccacdecdabaecabbedbdcbedcaaacbdddbdbaababbcadacbbcedccbcaddaedeadaaccbaabeebeadebbeceaebbdecadcdeceeacebbbadcecaddacbbddddcdbcbcddbcdccdbcbaedddbceccceedcaddcedcbdcaaaaadbdecabadddaeccccdbdcbbeadcdadcbeaadceddaeccddebbbedddceeccdacdeeeadaecccaacdddaaaeeccaacceaaabeeeeebbaecdaebbccaeadebcbdaaccbeeacdbededbadeebddadbbaadabeebeeccabdcabaddacbdcadddbdecbeecaceeabebdbcccaecbedbcbbddabceadadeaaeebddaaadbeacbebadcdbcdbbdeceaaeeebddecadacbcadddebaadecbebbebdcaccbdaecbdadaeecbcacbeabddabbabadeeeceedaecdaeaeadecacbbdddedccdcdddaaddbbcaaacdabcaccedbabcecbbbcdaedaeaccedbddddacbdcaabaeddecacbbdcaeeddcbeedcccbbaebacebbbddcaabbdceaccacaebacddbaaeeeaadeaeebcaebeacbbbeccbcdedeeeacaaacdaeaddacdeebcbeedeeedeecbecdaeccdeabccaaaadeedeedbcaeeaceeeecddeddedccdacdcbbdabdabeaabbdaecdcadedbdceecbcbaebdacebeabdaaacbccccdbabcceddedbccabcaddeaddcbcabcadadcebaaddbdddeadabbdebcecccaabaeaaabdaacbbbedbeebdacadcbdcdeeaeaeccaeebccabbaabcceecccdedaecceeacebebdbacabecaacabdbabacadedaabecdcbbbddaedaacacaeecabcabeaecdbcabcccabdddbaacbdedbcadccaadccadbaccbacebaecaedaadeeaaaedbcdeaadcebcceeadeccbcdcaacababdebddcececebaeabbeeeebdbcadcadcaacecbbcdedaceabbddbddbaddeaedeabeeddeaedcdbadebacbdbcacedebcbddcaecbbacbdaecaacabddcdbcaadbcdabbabcddeebeaadaabbeeddaddbbcaececcbdbeabedbccedaccdeccacaddbeaadaccbbaadecbbaadaddabaadbbdcdcecbaddcabacbbdcbbcadedcddccdedbbbbaceeadebebacecdcddbaeabdabccdaebedabccaddaeeacdbcebdbcdeadabcbdbadecdcdcddacdabceaaaaabacaeadbaaeeebcaedeeeddcceabdcabaaceeaabcbadceceaecbdaecedaabcdcdbeaeacbdbebaebceaacbeacdbedcdcbbddaebcabdcbeedadccaedbdcacbdedaddaaeaacbbbcccdddbbcddeaccdccddacaeebeeeabaacbdcdebdeddddaedaddbbcaaabdcbdbdbcedcaaecaddcaeeaddecbbceceaedecceaabbddbcebcbdcdaaadeebeceabdeccedcaaceabbeabbcededaabeedaecbaaabbadbeaddecdabdaabdcbdeeaabbaadcaabdebcbdeceeaebdeecccbacbaedeeaabdbadaceeedaddccbdaeceacdeebaabdcebbbeeeaaedaabacabdcebddbcedcacbbcdacbeeebdaeeabacdbacaedbddeeceededcdbbeecbceedbccdacbcbcbcbbacebeeeadcebdcbddecadcbaadbebeeeebdcbdbcbbdeabcbccabceeadeddbeeacdabebcceecceadecbbbdbcacdadabddddaccccdeaadbbcaecdddecebeeebbaaebccdbddadccebeecdcabcbaeebeaeceecbeaaaedecccbdedadbcdeebbddadeceecebadabaccdceaeacaaebdeaaededcbcbaddadaeaddadaedddadabebeadbdcccbeebdebdeaeddaeeeebbeccaecdaeeeabbcccddaeeccaccaddbeacbaeebcdaccddeabcbebcaccceeaecbbebbabaadddcbdadadcbaedddeabceebceeedecabbdabcdecaeebeccaabbddbeadcebcaebcaebadacecaaedbebbebeadacdaadbeeeeceeddbeededcdadcebcbdccbbacbebadcababceaccbddadcedccadadbabcedcebaacbcceaacdcdebbdbeeabddaeccabeebcdcaddeeeeeabcaacceaddedeabbcaceabacdbddebeadeeebcbbadddbdcbdedcbccadbaaaadadcdddabbddcdadadbbedddabcedcdccbbdeceaaaedbecbebbaeaeebbdbecacaaaacceeaeacbebebddecdbedabccccdcaedacdeadcbdcaaacaaeaccddbabdddeeeaebddbabdaeaebbaecabdedbbbdcdadbcecabbdeadedeececcabccadadcdbcbbbaaeddacbbdaedabcebbadbabcedcbeeeddddcdeeeaedddbdbceacbaddcbcebcdbceedcccceacedbbabeceeaeacddcbeaecceebbbadbddceeaeeeedddbcaceddeccbbeeeacacdacbbcaaebabeadbeeceeaacbebebacabaccabcddadaedacdeedabebacbaedaceedededbcdddaaacceebceedeeaddbdedbaadedbbbbcdbcbadddbdebdeaacbcdcccedbeadebbcaaaedbdeacecdaadbdccecdaeedaebeedabaaaeceecdacecedccbaebdbbdcddabecbdecbddeabdcdbaeeacdcbebececdbceacbdbddbdbddadeaaaccbbceeabcbbaccbeaecdceadcaecdaceecbdbdeddcdedcebacaecaaedbadaabbdbcabaaeabaececadedbecdadebccccbebabeadecbcbbbbeeaeadbbbacabbceaecdabaaccadeeeccadcaddcddaecceedbbadbeaebaacddadcddcccdaeaebacbdbdadaddaadabdcbcaeaaaecccaeaacaaeaeceedcdadccedcdecbebcbdeacaedacdbcebaeeecaaacdbceccdaeebdbacedbacebcdbaceedbaabdccdeababdcbbeaceedebccdadcaeddecdcaeccbbaabceddaeaeaeacdbbeadccccecaedccdaaaecadcaeceabeedbeacedbeeebbbedeadbaacaeaddcbdebbedbdecabaadddebddaccdeedabcdbdcddcbabbbbeecdeaeccebbdcccadccbbbbadeccdecbddcdebeabdbeddbecdabadaaadedbceaedacdedcbaebedcddddbbcbddcbbbbddabeceeebebcbbcbeaedcdcdeebaaeeabcdbccabeaedeaaaebbbeadddebcaaadcebccebabbdbadcddceddcaadcaddabbbebeedbeeaeaeadaeaedccbdeeeacbdbcbdbdbbeaebbcebadadcccacddbdebdbedeeecbaabacaadaeacceecaaacdddabbdcbddeaaabcdadccadaabdceedbeabedbbebdbebdcedccbcabbceacecdedcecebdbbcdaeacdceeabaededdddadbabacbcbdebdaaceacdbcbbcdbbebbaebbddbcecceecbaaeadaddcbdccbccecaacbdbbcececaccddcebdbcdbacdcdcadcaedccdcaacdeadaeadeaecbcdedcbaddacaecbebaaeeaadeedcbdacbbcedaccbcedccbbaebddedcdccbcacebbecaebeddeeaeabdbbebeacdbdceeacdeadaacebabdacabebbccbeedbcccbeaebbbcdeedecbaeddecaaeeebbecbcabbcbbdbabaddaaeaeceeedebeddaacdcadbddbdcecebdcdddabaaadecddbaaaecabaacebdceadaeeacddaeeeadbdeaeeebeecbaadeaecbaaccecdaceeebdedcecbcccecadbcbbddadaaebecdbaeedbaebdbdaceaaadbdecedbadeabaecceacbceccddabeaeaebdeacdcbcacbebdcbbbdbbaaebeededdbbbbbdacbebeeddddbaddedaacbaeebeadebbabaabbdeabcabeaaddeaeccccaddacbbddaeaeceadecddbdebabbeaedcbccaeccccebdedaaecdcccebcdbababbeadcbeccaacdedcdcdcbadbbdbbdeedacecbbeaaadacdcddbdbbccedaeacbaeacbdadadcbbadaaeabedcceccddecceabdceebdebaeabeceddaeccdeccbbddbbeabebcadeebbbeecaaebcedcdbdcadcbeccbbecbbeabeaededebdeececceedeceadcacccdacdaabdeeebedddaeaedcddebecbdebbbdeabcbabceeceadeecacbeeebdaaeeaedaecbebaebbdaccbeabecaedcbdedbedcacabaecbeaacdcdbeceadebcabdbeebacbaddbcecbabddabbdaaaabeecabbebecbbbedebedcebbcbeecacececeeccacdcdcccdcadcacebcceeceacdebbbebccacbeaebccbdcedaacdeaeebdbdaeaeaccceecdaabeccebbbeecaeeeebedccadbceeaacbcdadccbcecdedeeaeddeddebeeddcbebcacadbaaadaccedaddbcacacbcaedcddadbeaddacedeebabcddcbddbaedbeaeceadadcbceebcebaedabdbcdaabeeaaebbbeddaeddeacdcdadbeceaaceecbcbbaccbbcecbccccecaddacddeabbbbedbaddbabaabbcaaeddbdabaecbbeaeeebdbecddabaaccdaaaddabccbdababebeecccbccadebcdadcddaeeaecabadbbddadabcbaccecbcaeeedcaaadcbcdadebbcadddbeedbedbededbdddcecceebaeaedebcdcbecaeeedddccbaebaaeabdcacbaedcddebcaecbbbdabdbcdaaabcaccacecbeadddbdaecadcabdededbccebeebebcccebbeaedddbecedeedddccbdbcacbccaaebacadecedbaddbdeeadbdddbdbbeecaccedadeccdddeaddbddeaedacecebadbdcdcbdececbeebadbbbcdeeeeccccadbbdadcdebcadcbdbadcecedcacaeeadeaccaedbeadcedaaebbaaeebaaabdaedeacccbdbdadeddcaecccbeeaccddddadaacbaaddcdceeeadcdcabbbcaaabcdcceadbbebdcacddcabcacbbaadddcbbdeccbaebbacedcccccabcbcdcbddacceeddedbeccedbcbcaabecabaccbeebcebbbcdacddbbbeaedecdececdcadedcbdaeedeceebedcbdbdbedcbdebbaececcbeddcacddbbdadcabaeacebcdbcbbebdaacdbbaeaabbcdabbbdadbbadddeebdccdabbaeacaddcabbccccbecebaadaeedbdcdcaeaaebaaccbaaddcdaedcebbeadaaeabdaeddaacaacbdabbbebcbbcccaecaccccebbeededdcdbaabaccccddddceaacbbaeadeccecddbdeabebbeebedbcabaaaeccbceadbcceedebcadcedaceecaacaabecbbbededcddeaededeeadeeedecabeddbbddcadacccdcceccdaabadcceddacbdeebacbadebcdcbeebeabaddebcebaaecdecdaadbbababeaccddaeecdecabdeccbcedeacceedebdaaabaaeabeeeededacaebdbeeeceebcddbedeeeddaaacaedebaeabdcccddbedbdbbededadeaaaddcdeaebccddabcbebbdccbcadabceedccecdccddcdddbcadbddbcecdddabbacbdceadaeeaaeaceecaaaedcaaebaccedbbacaccdbdeabcaaedebacbdeaadabecaeaadddccabacabbcdbaceeeacddbcedaeeaddeececbdacdceecccdecabaddbacabcadadeaaeeacceaaadadaccececedceededaabecadcacdcabcabbaebeeceddbedeccdddedaecbdeeaaaabcbeaaeaabadabbdebceaaaddceaedadadcabaaecaddbdbdedceaacbbceddeceeddbccdeeedbbcabaabaadeaebbceeccabdbbbecbedceadcaacdecabbdddaaecbaddabcbccbcdbbdbaeedbbabdeebebaeedcbebdaebaccadcbeeedbedcdcddbeeaabececccaaceabbdeaedcaaaaaceebccecbebcbbbbdaeeebbcebdbacdccebcadcbbcabdccaaebcdecdabbdcdccbccbdbccaceceeeaebdddeeeaeedbcccdebcaeadeadbcebabaacabecbdaaeaddbebceeedcdbdadecdcdcdbbceacddccaadceadbaeaeeaaeaabecbadeecdeaecbbaeaaadbcaededbcccaaeeebbeccadebbccbabaaacedeccdbbaeaaedbedcaaddceedddbcddbdecebaecbbcbdcacabedeadacaaadbedbcebabecbcbbcaddebaabcabaebdddcccabbceedebdeeacbebcedacddcdcbddbbcebecbadbaaccadcaaaccbbddbddeddbabaddeaceedddcaeaadbeacacadabaabaacabcccedacdcbabaedabdcbddcecaeeebdeedeaedababdbeddadcccecdadcdebdbaebaaadadeaacdcbdcdebcaccbbcbeecbcabcaadceaedebeacceaeedcaecaadeadeeebecdbadadabaaeaaebbcabbdddbabddcbcdbdeaededdebaddbbaebaceaeabcebecaccecacacbaceaebaaadadecdbabebebbdbdcedcebacceeceaeedadcadadedaaeccadedbcebeaceacedbaddecdeadbbcdabdeecadaeaceceadbaaaaebcbbcacacacaacbbdddbddecedeecedaaaebcecbddbdbbbbdcacdcdcedaacacebabeaddbeeaaadcecbecddabbcacadabccbccaeddcaeebecbedbaadbccabcaebceebbebbbacccebaebaeccedeaadadeaacbcaecebcbebbcdceebdedeadeeceddabecbbabdaeddbeecbedcdccbddaceeccabecaeecccaddebccdededbadbdaddeaebecbeadadaadcacbabbbddaaadececbbbaedaabcdbdddeaddaaecbabebcaeccbaeaccdccacbcaabadbcdcacaeddddbcbecacebeeabadbabceebacbadaecaaaceaaecdddabebdeecbbaaddcebaeabedabcacdbbeaecedceacbbbbabbcdccdebeaabacccea"
                ) == 1628
        );
    }
}
