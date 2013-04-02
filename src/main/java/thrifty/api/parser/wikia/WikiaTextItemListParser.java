package thrifty.api.parser.wikia;

import org.springframework.stereotype.Component;
import thrifty.api.parser.ItemListParser;
import thrifty.api.parser.Parseable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WikiaTextItemListParser implements ItemListParser {

    public static void main(String[] args) {
        String resp = "{\\\"query\\\":{\\\"pages\\\":{\\\"2970\\\":{\\\"pageid\\\":2970,\\\"ns\\\":10,\\\"title\\\":\\\"Template:Items\\\",\\\"revisions\\\":[{\\\"*\\\":\\\"<div style=\\\\\\\"border-top:2px solid #BBB;border-bottom:2px solid #BBB;\\\\\\\">\\\\n<div\n" +
                " class=\\\\\\\"va-collapsible-content mw-collapsible {{#ifeq:{{{1}}}|show||mw-collapsed}}\\\\\\\" data-expandtext=\\\\\\\"show\\\\\\\" data-collapsetext=\\\\\\\"hide\\\\\\\">\\\\n<div style=\\\\\\\"text-align:center\n" +
                ";font-size:120%\\\\\\\">'''List of [[Item]]s'''<\\\\/div>\\\\n<div class=\\\\\\\"va-collapsible-content mw-collapsible-content\\\\\\\">\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class\n" +
                "=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"3\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Consumable item|Consumable]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\\n" +
                "n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Gr\n" +
                "ounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Crystalline Flask}}\\\\n{{iio|Elixir of Brilliance}}\\\\n{{iio|Elixir of Fortitude}}\\\\n{{iio|Explorer's Ward\n" +
                "}}\\\\n{{iio|Oracle's Elixir}}\\\\n{{iio|Ruby Sightstone}}\\\\n{{iio|Sightstone}}\\\\n{{iio|Sight Ward}}\\\\n{{iio|Vision Ward}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio\n" +
                "|Crystalline Flask}}\\\\n{{iio|Ichor of Illumination}}\\\\n{{iio|Ichor of Rage}}\\\\n{{iio|Oracle's Extract}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Crystalline\n" +
                "Flask}}\\\\n{{iio|Oracle's Extract}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Elixir of Brilliance}}\\\\n{{iio|Elixir of Fortitude}}\\\\n{{iio|Oracle's Extract}}\\\\n\n" +
                "|-\\\\n!Common\\\\n| colspan=\\\\\\\"5\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Health Potion}}\\\\n{{iio|Mana Potion}}\\\\n{{iio|Total Biscuit of Rejuvenation}}\\\\n|}\\\\n{| border=\\\\\n" +
                "\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"4\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Basic item|Basic]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowsp\n" +
                "an=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n!\n" +
                " style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n{{iio|Hunter's Machete}}\\\\n\n" +
                "|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Prospector's Blade}\n" +
                "}\\\\n{{iio|Prospector's Ring}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" st\n" +
                "yle=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Amplifying Tome}}\\\\n{{iio|B. F. Sword}}\\\\n{{iio|Blasting Wand}}\\\\n{{iio|Boots of Speed}}\\\\n{{iio|Brawler's Gloves}}\\\\n{{iio|Chain Vest}}\\\\n{{ii\n" +
                "o|Cloak of Agility}}\\\\n{{iio|Cloth Armor}}\\\\n{{iio|Dagger}}\\\\n{{iio|Doran's Shield}}\\\\n{{iio|Faerie Charm}}\\\\n{{iio|Giant's Belt}}\\\\n{{iio|Long Sword}}\\\\n{{iio|Needlessly Large\n" +
                "Rod}}\\\\n{{iio|Negatron Cloak}}\\\\n{{iio|Null-Magic Mantle}}\\\\n{{iio|Pickaxe}}\\\\n{{iio|Recurve Bow}}\\\\n{{iio|Rejuvenation Bead}}\\\\n{{iio|Ruby Crystal}}\\\\n{{iio|Sapphire Crystal}}\\\\\n" +
                "n\\\\n|-\\\\n![[Champion]]\\\\n| colspan=\\\\\\\"3\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Bonetooth Necklace}}\\\\n{{iio|The Hex Core}}\\\\n\\\\n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\"\n" +
                " cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"4\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Advanced item|Advanced]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\n" +
                "\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width:\n" +
                "18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian Angel}}\\\\n{{iio|Madred's Razors}}\\\\n{{iio|Mejai's Soulstealer}}\\\\n{{iio|Rab\n" +
                "adon's Deathcap}}\\\\n{{iio|Sightstone}}\\\\n{{iio|Spirit Stone}}\\\\n{{iio|Sword of the Occult}}\\\\n{{iio|Seeker's Armguard}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Warmog's Armor}}\\\\n|style=\\\\\\\"v\n" +
                "ertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Overlord's Bloodmail}}\\\\n{{iio|Wooglet's Witchcap}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian Angel}}\n" +
                "\\\\n{{iio|Kitae's Bloodrazor}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Wooglet's Witchcap}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian's Horn}}\\\\n{{iio|Kitae's Bloo\n" +
                "drazor}}\\\\n{{iio|Rabadon's Deathcap}}\\\\n{{iio|Seeker's Armguard}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Warmog's Armor}}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:\n" +
                "left;\\\\\\\"|\\\\n{{iio|Abyssal Scepter}}\\\\n{{iio|Avarice Blade}}\\\\n{{iio|Berserker's Greaves}}\\\\n{{iio|Boots of Mobility}}\\\\n{{iio|Boots of Swiftness}}\\\\n{{iio|The Brutalizer}}\\\\n{{ii\n" +
                "{\\\"query\\\":{\\\"pages\\\":{\\\"2970\\\":{\\\"pageid\\\":2970,\\\"ns\\\":10,\\\"title\\\":\\\"Template:Items\\\",\\\"revisions\\\":[{\\\"*\\\":\\\"<div style=\\\\\\\"border-top:2px solid #BBB;border-bottom:2px solid #BBB;\\\\\\\">\\\\n<div\n" +
                " class=\\\\\\\"va-collapsible-content mw-collapsible {{#ifeq:{{{1}}}|show||mw-collapsed}}\\\\\\\" data-expandtext=\\\\\\\"show\\\\\\\" data-collapsetext=\\\\\\\"hide\\\\\\\">\\\\n<div style=\\\\\\\"text-align:center\n" +
                ";font-size:120%\\\\\\\">'''List of [[Item]]s'''<\\\\/div>\\\\n<div class=\\\\\\\"va-collapsible-content mw-collapsible-content\\\\\\\">\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class\n" +
                "=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"3\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Consumable item|Consumable]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\\n" +
                "n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Gr\n" +
                "ounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Crystalline Flask}}\\\\n{{iio|Elixir of Brilliance}}\\\\n{{iio|Elixir of Fortitude}}\\\\n{{iio|Explorer's Ward\n" +
                "}}\\\\n{{iio|Oracle's Elixir}}\\\\n{{iio|Ruby Sightstone}}\\\\n{{iio|Sightstone}}\\\\n{{iio|Sight Ward}}\\\\n{{iio|Vision Ward}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio\n" +
                "|Crystalline Flask}}\\\\n{{iio|Ichor of Illumination}}\\\\n{{iio|Ichor of Rage}}\\\\n{{iio|Oracle's Extract}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Crystalline\n" +
                "Flask}}\\\\n{{iio|Oracle's Extract}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Elixir of Brilliance}}\\\\n{{iio|Elixir of Fortitude}}\\\\n{{iio|Oracle's Extract}}\\\\n\n" +
                "|-\\\\n!Common\\\\n| colspan=\\\\\\\"5\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Health Potion}}\\\\n{{iio|Mana Potion}}\\\\n{{iio|Total Biscuit of Rejuvenation}}\\\\n|}\\\\n{| border=\\\\\n" +
                "\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"4\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Basic item|Basic]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowsp\n" +
                "an=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n!\n" +
                " style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n{{iio|Hunter's Machete}}\\\\n\n" +
                "|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Prospector's Blade}\n" +
                "}\\\\n{{iio|Prospector's Ring}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Doran's Blade}}\\\\n{{iio|Doran's Ring}}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" st\n" +
                "yle=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Amplifying Tome}}\\\\n{{iio|B. F. Sword}}\\\\n{{iio|Blasting Wand}}\\\\n{{iio|Boots of Speed}}\\\\n{{iio|Brawler's Gloves}}\\\\n{{iio|Chain Vest}}\\\\n{{ii\n" +
                "o|Cloak of Agility}}\\\\n{{iio|Cloth Armor}}\\\\n{{iio|Dagger}}\\\\n{{iio|Doran's Shield}}\\\\n{{iio|Faerie Charm}}\\\\n{{iio|Giant's Belt}}\\\\n{{iio|Long Sword}}\\\\n{{iio|Needlessly Large\n" +
                "Rod}}\\\\n{{iio|Negatron Cloak}}\\\\n{{iio|Null-Magic Mantle}}\\\\n{{iio|Pickaxe}}\\\\n{{iio|Recurve Bow}}\\\\n{{iio|Rejuvenation Bead}}\\\\n{{iio|Ruby Crystal}}\\\\n{{iio|Sapphire Crystal}}\\\\\n" +
                "n\\\\n|-\\\\n![[Champion]]\\\\n| colspan=\\\\\\\"3\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Bonetooth Necklace}}\\\\n{{iio|The Hex Core}}\\\\n\\\\n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\"\n" +
                " cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"4\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Advanced item|Advanced]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\n" +
                "\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width:\n" +
                "18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian Angel}}\\\\n{{iio|Madred's Razors}}\\\\n{{iio|Mejai's Soulstealer}}\\\\n{{iio|Rab\n" +
                "adon's Deathcap}}\\\\n{{iio|Sightstone}}\\\\n{{iio|Spirit Stone}}\\\\n{{iio|Sword of the Occult}}\\\\n{{iio|Seeker's Armguard}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Warmog's Armor}}\\\\n|style=\\\\\\\"v\n" +
                "ertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Overlord's Bloodmail}}\\\\n{{iio|Wooglet's Witchcap}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian Angel}}\n" +
                "\\\\n{{iio|Kitae's Bloodrazor}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Wooglet's Witchcap}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Guardian's Horn}}\\\\n{{iio|Kitae's Bloo\n" +
                "drazor}}\\\\n{{iio|Rabadon's Deathcap}}\\\\n{{iio|Seeker's Armguard}}\\\\n{{iio|Thornmail}}\\\\n{{iio|Warmog's Armor}}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:\n" +
                "left;\\\\\\\"|\\\\n{{iio|Abyssal Scepter}}\\\\n{{iio|Avarice Blade}}\\\\n{{iio|Berserker's Greaves}}\\\\n{{iio|Boots of Mobility}}\\\\n{{iio|Boots of Swiftness}}\\\\n{{iio|The Brutalizer}}\\\\n{{ii\n" +
                "o|Catalyst the Protector}}\\\\n{{iio|Chalice of Harmony}}\\\\n{{iio|Emblem of Valor}}\\\\n{{iio|Fiendish Codex}}\\\\n{{iio|Glacial Shroud}}\\\\n{{iio|Guinsoo's Rageblade}}\\\\n{{iio|Haunti\n" +
                "ng Guise}}\\\\n{{iio|Hexdrinker\\\\u200e}}\\\\n{{iio|Hextech Revolver\\\\u200e}}\\\\n{{iio|Infinity Edge}}\\\\n{{iio|Ionian Boots of Lucidity}}\\\\n{{iio|Kage's Lucky Pick}}\\\\n{{iio|Kindlegem}\n" +
                "}\\\\n{{iio|Last Whisper}}\\\\n{{iio|Malady}}\\\\n{{iio|Mana Manipulator}}\\\\n{{iio|Mercury's Treads}}\\\\n{{iio|Ninja Tabi}}\\\\n{{iio|Phage}}\\\\n{{iio|Philosopher's Stone}}\\\\n{{iio|Quicksi\n" +
                "lver Sash}}\\\\n{{iio|Runaan's Hurricane}}\\\\n{{iio|Rylai's Crystal Scepter}}\\\\n{{iio|Sheen}}\\\\n{{iio|Sorcerer's Shoes}}\\\\n{{iio|Stinger}}\\\\n{{iio|Sunfire Cape}}\\\\n{{iio|Sword of t\n" +
                "he Divine}}\\\\n{{iio|Tear of the Goddess}}\\\\n{{iio|Tiamat}}\\\\n{{iio|Vampiric Scepter}}\\\\n{{iio|Void Staff}}\\\\n{{iio|Warden's Mail}}\\\\n{{iio|Wit's End}}\\\\n{{iio|Zeal}}\\\\n\\\\n|-\\\\n![[C\n" +
                "hampion]]\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Head of Kha'Zix}}\\\\n{{iio|Augment: Death}}\\\\n{{iio|Augment: Gravity}}\\\\n{{iio|Augment: Power}}\\\\n\\\\\n" +
                "n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"3\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Legendary item|Legendary]\n" +
                "]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width:\n" +
                " 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Banshee's Veil}}\\\\n{{iio|The Bloodthirster\n" +
                "}}\\\\n{{iio|Liandry's Torment}}\\\\n{{iio|Homeguard}}\\\\n{{iio|Ohmwrecker}}\\\\n{{iio|Ruby Sightstone}}\\\\n{{iio|Spirit of the Ancient Golem}}\\\\n{{iio|Spirit of the Elder Lizard}}\\\\n{{\n" +
                "iio|Spirit of the Spectral Wraith}}\\\\n{{iio|Wriggle's Lantern}}\\\\n{{iio|Zhonya's Hourglass}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Banshee's Veil}}\\\\n{{ii\n" +
                "o|Blackfire Torch}}\\\\n{{iio|Grez's Spectral Lantern}}\\\\n{{iio|Hextech Sweeper}}\\\\n{{iio|Ohmwrecker}}\\\\n{{iio|Sanguine Blade}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"\n" +
                "|\\\\n{{iio|Blackfire Torch}}\\\\n{{iio|Entropy}}\\\\n{{iio|Grez's Spectral Lantern}}\\\\n{{iio|Hextech Sweeper}}\\\\n{{iio|Odyn's Veil}}\\\\n{{iio|Sanguine Blade}}\\\\n|style=\\\\\\\"vertical-alig\n" +
                "n:top; text-align:left;\\\\\\\"|\\\\n{{iio|Banshee's Veil}}\\\\n{{iio|Entropy}}\\\\n{{iio|Hextech Sweeper}}\\\\n{{iio|Liandry's Torment}}\\\\n{{iio|Sanguine Blade}}\\\\n{{iio|Zhonya's Hourglass}\n" +
                "}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Aegis of the Legion}}\\\\n{{iio|Archangel's Staff}}\\\\n{{iio|Athene's Unholy Grail}}\\\\n{{iio|At\n" +
                "ma's Impaler}}\\\\n{{iio|Banner of Command}}\\\\n{{iio|Bilgewater Cutlass\\\\u200e}}\\\\n{{iio|The Black Cleaver}}\\\\n{{iio|Deathfire Grasp}}\\\\n{{iio|Eleisa's Miracle}}\\\\n{{iio|Alacrity}\n" +
                "} <!--(Pretend they have Enchantment in their name, as they do in-client)-->\\\\n{{iio|Captain}}\\\\n{{iio|Distortion}}\\\\n{{iio|Furor}}\\\\n{{iio|Executioner's Calling}}\\\\n{{iio|Fro\n" +
                "zen Heart}}\\\\n{{iio|Frozen Mallet}}\\\\n{{iio|Iceborn Gauntlet}}\\\\n{{iio|Lich Bane}}\\\\n{{iio|Locket of the Iron Solari}}\\\\n{{iio|Manamune}}\\\\n{{iio|Maw of Malmortius}}\\\\n{{iio|Mer\n" +
                "curial Scimitar}}\\\\n{{iio|Mikael's Crucible}}\\\\n{{iio|Morellonomicon}}\\\\n{{iio|Nashor's Tooth}}\\\\n{{iio|Phantom Dancer}}\\\\n{{iio|Randuin's Omen}}\\\\n{{iio|Ravenous Hydra}}\\\\n{{ii\n" +
                "o|Rod of Ages}}\\\\n{{iio|Shard of True Ice}}\\\\n{{iio|Shurelya's Reverie}}\\\\n{{iio|Spirit Visage}}\\\\n{{iio|Statikk Shiv}}\\\\n{{iio|Trinity Force}}\\\\n{{iio|Twin Shadows}}\\\\n{{iio|Wi\n" +
                "ll of the Ancients}}\\\\n{{iio|Youmuu's Ghostblade}}\\\\n{{iio|Zeke's Herald}}\\\\n{{iio|Zephyr}}\\\\n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" s\n" +
                "tyle=\\\\\\\"width: 100%; \\\\\\\"\\\\n! rowspan=\\\\\\\"3\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|[[Mythical item|Mythical]]\\\\n! colspan=\\\\\\\"1\\\\\\\" rowspan=\\\\\\\"2\\\\\\\" style=\\\\\\\"width: 14%;\\\\\\\"|Maps\\\\n! style=\\\\\\\"width: 18%;\\\\\n" +
                "\\\" |[[Summoner's Rift]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Twisted Treeline]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Dominion]]\\\\n! style=\\\\\\\"width: 18%;\\\\\\\" |[[Proving Grounds]]\\\\n|-\\\\n|style=\\\\\\\"v\n" +
                "ertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Blade of the Ruined King}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Blade of the Ruined King}}\\\\n{{iio|The Li\n" +
                "ghtbringer}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|The Lightbringer}}\\\\n|style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Blade of the Ruined King\n" +
                "}}\\\\n|-\\\\n!Common\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Runic Bulwark}}\\\\n{{iio|Hextech Gunblade\\\\u200e}}\\\\n{{iio|Muramana}}\\\\n{{iio|Seraph's Embrac\n" +
                "e}}\\\\n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! colspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"width: 28%;\\\\\\\"|[[Removed items]]\\\\n| co\n" +
                "lspan=\\\\\\\"3\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"text-align:left;\\\\\\\"|\\\\n{{iio|Blue Pill}}\\\\n{{iio|Cloak and Dagger}}\\\\n{{iio|Elixir of Agility}}\\\\n{{iio|Force of Nature}}\\\\n{{iio|Heart of Gol\n" +
                "d}}\\\\n{{iio|Innervating Locket}}\\\\n{{iio|Ionic Spark}}\\\\n{{iio|Leviathan}}\\\\n{{iio|Lord Van Damm's Pillager}}\\\\n{{iio|Madred's Bloodrazor}}\\\\n{{iio|Meki Pendant}}\\\\n{{iio|Moonfl\n" +
                "air Spellblade}}\\\\n{{iio|Priscilla's Blessing}}\\\\n{{iio|Regrowth Pendant}}\\\\n{{iio|Sage's Ring}}\\\\n{{iio|Soul Shroud}}\\\\n{{iio|Stark's Fervor}}\\\\n{{iio|Wicked Hatchet}}\\\\n{{iio|\n" +
                "Zhonya's Ring}}\\\\n|}\\\\n{| border=\\\\\\\"0\\\\\\\" cellpadding=\\\\\\\"1\\\\\\\" cellspacing=\\\\\\\"1\\\\\\\" class=\\\\\\\"navbox hlist\\\\\\\" style=\\\\\\\"width: 100%; \\\\\\\"\\\\n! style=\\\\\\\"width: 28%;\\\\\\\"|[[Alpha items|Alpha\\\\/Beta\n" +
                " Items]]\\\\n| colspan=\\\\\\\"4\\\\\\\" rowspan=\\\\\\\"1\\\\\\\" style=\\\\\\\"vertical-align:top; text-align:left;\\\\\\\"|\\\\n{{iio|Ancient Pocket Watch}}\\\\n{{iio|Bag of Tea}}\\\\n{{iio|Breathstealer}}\\\\n{{iio|Ma\n" +
                "rksman's Rifle}}\\\\n{{iio|Oracle's Hood}}\\\\n{{iio|Pendant of Zephiris}}\\\\n{{iio|Renewal Tunic}}\\\\n{{iio|Rejuvenation Potion}}\\\\n{{iio|The Rose's Pride}}\\\\n{{iio|Scroll of Telepo\n" +
                "rtation}}\\\\n{{iio|Wizard's Regalia}}\\\\n{{iio|Yordle Stompers}}\\\\n|}\\\\n<\\\\/div><\\\\/div><\\\\/div>\\\\n<noinclude>{{documentation}}<\\\\/noinclude>\\\"}]}}}}";

        WikiaTextItemListParser p = new WikiaTextItemListParser();
        List<String> items = p.toItemList(new WikiaText(resp, null));

        System.out.println(items);
    }

    public List<String> toItemList(Parseable parser) {
        List<String> items = new ArrayList<String>();
        String wikiText = parser.getStringValue();

        String itemPatternStr = "\\{\\{iio\\|[\\w '\\n\\\\]*}}";
        Pattern itemPattern = Pattern.compile(itemPatternStr);
        Matcher matcher = itemPattern.matcher(wikiText);

        while(matcher.find()) {
            String item = matcher.group().replace("\n", "").replace("{{iio|", "").replace("}}", "");
            items.add(item);
        }

        return items;
    }
}
