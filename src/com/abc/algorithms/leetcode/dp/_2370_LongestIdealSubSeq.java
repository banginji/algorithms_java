package com.abc.algorithms.leetcode.dp;

import java.util.Arrays;

public class _2370_LongestIdealSubSeq {
    private static int longest(String s, int k) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        char[] ca = s.toCharArray();
        int maxLength = Integer.MIN_VALUE;
        for (int idx = 1; idx < ca.length; idx++)
            maxLength = Math.max(maxLength, 1 + longest(idx, idx - 1, ca, dp, k));
        return maxLength;
    }

    private static int longest(int idx, int prevIdx, char[] ca, Integer[][] dp, int k) {
        if (idx >= ca.length || prevIdx >= ca.length) return 0;
        if (dp[idx][prevIdx] != null) return dp[idx][prevIdx];
        if (Math.abs(ca[idx] - ca[prevIdx]) <= k)
            return dp[idx][prevIdx] = Math.max(
                    1 + longest(idx + 1, idx, ca, dp, k),
                    longest(idx + 1, prevIdx, ca, dp, k)
            );
        else
            return dp[idx][prevIdx] = longest(idx + 1, prevIdx, ca, dp, k);
    }

    private static int bs(int[] nums, int k) {
        int size = 0, tail[] = new int[nums.length];
        for (int num : nums) {
            int idx = Arrays.binarySearch(tail, 0, size, num);
            if (-1 * idx - 1 == size) size++;
            if (idx < 0) tail[-1 * idx - 1] = num;
        }
        return nums.length - size;
    }

    public static void main(String[] args) {
        System.out.println(
                longest("acfgbd", 2)
        );

        System.out.println(
                longest("abcd", 3)
        );

        System.out.println(
                longest("ekwjngqlwjgne", 4)
        );

        System.out.println(
                longest("pvjcci", 4)
        );

        System.out.println(
                longest(
                        "bddyymndvdrwwpmqtrqbsexxxmctobdsakewgiyzeughcarompbhqnlbavdamagbsiserfwnpamxgcgwqygwxnshtlubpogtvvolfwwsyxyumeeywvoleuzatygluetywvbssuzmsiugwurrejnibbxitytfusfwjvklhqisiqefpxsqvtqxrpwrreaaqqddveqlpswvdhydfmrhhrsdqizhzucgcwbqpuejscehcvnpgspynekckaguyybqqfoweyfbrbnerbpuevtzyjwaydiburlesqswuwpivywabnymnukdweknuwpcujfwnngnfgmymdqljdvheujnlvctlrbxuwdlemvethgjcmsqwbsbmbrsopayqkxdeaifwouupggbpdgojjrfntzapwvdufnpcpdexxjddlzwioebndkppsynzrqtqgdiznombihpibpqlzxnvwrqobbxqnvnidgvdwhxkbxclwqarukhlbpvxaokpzeqdhusdtdrbflwamebmmmuwrjyzluxtffteczuwaljzuoztrpxphamjhumzzxushclydqiqlrcxslvivngpcbclzqsclktfhrarcmiordhfvrezmiljfppkrkjybgajgtccrkcoiltbikdosbjepbnqegsisnuuoyufhdpyhbsacvjzelalkxgvvcajjwjyazoilwmfvvcdoyznlgkjfwmkbmxoconljqcjmcbgpavhzhvicjlbjivlprrxfhjpazqfmunuphobievjgxxndtwbcoubwoeeygzdogwmyvrtqwzqztwbuzhbwohatornjpgnyvqxuyiqfxamfnsbsrdpahvcwslsltgtjvttufykpygzvqtwbauvvklydheezojkojpefrynqqqfgjgmmcnkubxlwlaqfssfvdgtxoovzzkrelhfuharbwpdqcrvczatzcngrpgxbyqjhllbznemsutgolbasvmlkwcidpvhshgaujxwsxmewntrtpyfudrbeoaejkpswarwjfkhrelhmydsqpgiumegvmalejmzpliyzvgkuowlpqyjektfwmcsritydgipqjjaezplzxrcbltsbqvdxcxtwvtjugkjwxqmgjdetlshicjfpcdahamynkfrmxvlffjjbedhwmorgctrkuoemkdrkuzuuhdnvblrblcolzuoyouubmbkxntdfpfzvzenyacuezvmkaueazlfgvfksfuaenqusxiobvgsvhmpuflsjuaprazkzlaopzzdkzbrathcjjxujaskbmaetrijpfolcwvvmfvqzquqpocgpmfnyswpcdzxuginkwgxjzzabmalbdotrswjiszhmmocymfxooeknucfxsfotsnlyeiwepxarzkwpihaucqlukednrzapekiuhtetfkycoxneyljzcctajwjnbjqolxijtlntfvzehdlvqzgsndtwpmkdefrmwfhougsxpdwwtknivmngquddehbwllltdepepbcpzobabilwallwrhrsuuhpcroehlunnteqddnqiyzeeuqsiypppmzybtcqnbffgegqyfayspohvoufnwporitdcddbglumguhrjjcylmmjdimatpgpfttmxlruyplhqpqzgkksegpansckweozvfacqqrhitbpvhmvskmmkoxhaedoiiwgpkwewqvocwcqlrprkvdgmabageykianbkumodyvibxltqclscjpjxeqmonnwlqdwlyxlkxwbwuwahmfqibosizoxbbkntijqiffzadtdsbkcvasmqhlyvtsciknscqcdvviarrzcqjmvrpxtiygdabfqrdxcchmpdoqpcfnmqcvxglwerezbtbwtaoubjzozjulzxdfxlgjislmysfoiuoieydqfzjrirvnyfeadimxcbbmktwmhxtdqcyjwtnbulnfkmegpcxlprishcwvfinfxuizwstozdpwmyrzrhujruyomlfoeijlrjvqtydxullafudkosxamqlvwdfyclpujiazzqcebzujyybrhdyyunrggskmwucaolygtthxdenyfnqzmhartpxmywlllneodwhlqrtnrihsazvuvzdvmanwtqdpggjbzyubakikoltxrkbwxjdmxccexsvhdsunfcsltiqgjwwulysntvhirlqdkpzddaqzibelljkdgzyckdpdupvymlsfvmsiioxzityehaavmzubfmrbeiiksezftxkadcdffpsbppsroiuvixnxqzbdzysjclkequvibsdfychmjwtmqpvrkhqdpujhbgwcnwoezxyltmnhuffnwvfmcgwfkzzyiloiwzigmpyewobdhczmqivovvftafgqofwnytktebemwmzlnrmjmcdumipwoxnmrgiwvueumsyyjgjqhvzzfupbbxsziknirbpqhzccmzqzylpsmfslpxvzrkzimmynjfrhjdrbrlxasqhjngbxfhqgjcfggrgxnzxqjolppjbxrqmzcskeycuokvsupxzqqmgnupstqgegzakaleqqpukojdwzttdsiabcpmasmrfzqjlfosanrgowduxgzlyddobjdtvefshvimktaitzmstawbaswdrsdqutuzvurlnsuflnmcwzzvnlzfipssdpcyzqzvzpeyytuocovjpldwyturdjctgupogefzlhiodxzedchcjdqewihquwtpibabvjtgscmzjxtilqbcjejwsipnzdxybxnkfjidhhviqgmcnsunqaeobrqbbzgawukbcdaokzkheloqobsiugadqzflqewwsegmdyrhndkzwpfsihrfvrjpzwcdrmctkexehcovdvqohwzckfyfnridhrsnabyradrohzrtldshyhdeaofxundjiejjxxdjflgzbdlgrmbdtgqeepfhpfshshvsfdfbygxluglovyfuhdgjtddeisnxzegknoxfmmnfmwbbgkswgcomnfovjfnwtuykzqnuxdauuxghigwahkxgupbigdvppktgqqhxqvhssdtfuqdtzzayqlufjglpujktaxkzudqojxrdsajjqavprehdtkyuauljlleatxxodxbbbxschzyvqvaijpwypgnqtqxkeescrhsocsqeaytzylzlyktjzckhovzblvhijtkqhhasujxbwdiicqbrlhvpvrwgjrhpyjvkuhyypfqleigigtjugahsdqzacfgqkaurvogabuxynvnhsjqkboxxvhjcierstzoiroxkeccfhccancohonsbdujpxgzqphlpgyszrlocvturfeyhgppxnfofhbsjjtezxvfunstewcamilxgguhwzkpcrgcnsdspjzbcrnaqkvsqibcqelauqjnxrpftfsighyinhdxvxfbgvtnrycfnbuvnlsxgnldbzvxagrlulkmxtqfxohsqhtciqd",
                        17
                )
        );
    }
}
