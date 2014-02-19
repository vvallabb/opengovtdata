<?php
class Candidates extends CI_Model {

	public function __construct(){
		$this->load->database();
	}
	
	public function get_candidates(){
		$query = $this->db->get('knowyourcandidates.MP2014');
		return $query->result_array();
	}
	/**
	* This method parses the json from a file and persists to a DB table
	Example:
	{
    "Place of Birth": "Bero, Distt. Purulia (West Bengal)",
    "No.of Daughters": "2",
    "Mother's Name": "Smt. Konak Lata Acharia",
    "Party Name": "Communist Party of India (Marxist)(CPI(M))",
    "Profession": "Trade Unionist Social Worker",
    "Date of Marriage": "25 Feb 1975",
    "Constituency": "Bankura (West Bengal )",
    "Date of Birth": "11 Jul 1942",
    "Educational Qualifications": "M.A.,B.T. Educated at Ranchi University, Ranchi, Bihar and Kolkata University, Kolkata, West Bengal",
    "No. of Sons": "1",
    "Other Information": "(i) West Bengal Railway Contracter Labour Union; (ii) Purulia District Committee (CITU); (iii) LIC Agent organisation of India; (iv) DVC Contractor Workers Union; (v) Santhaldihi Thermal Power Plant, Thikader Shramik Union; (vi) Damodar Cement and Slag Workers Union; (i) All India Centre, Indian Trade Union; (ii) DVC Shramik Union and (iii) Colliery Mazdoor Sabha of India; (i) Central Committee CPI (M); (ii) General Council, CITU; (iii) Working Committee, CITU, West Bengal",
    "Present Address": "\n21, Ashoka Road,\nNew Delhi - 110 001\nTels. (011) 23342235, 23362235, 23034705 9868180022(M), 09434014347 (M)\n\n\n\n",
    "Email Address :": "bacharya@sansad.nic.in",
    "Name": "Acharia,Shri Basudeb",
    "Marital Status": "Married",
    "Countries Visited": "Iraq (Baghdad), Former U.S.S.R.; Member, Indian Parliamentary Delegations to Mongolia, (1985); and U.K., (1990); participated in International Conference on `Peace and Solidarity` at Pyongyang, North Korea; visited Oman, Poland, Czech Republic, Italy and Slovak Republic as member of Delegation led by the President of India, (1996); attended (i) UN General Assembly Session, (1999); (ii) World Solidarity Conference, Havana, (2000); (iii) Solidarity and fraternal relation between CITU and Zenroren, National Confederation of Trade Unions, Tokyo,Japan,(September,2009) and the World Federation of Trade Unions, Brussels, (October 2009)",
    "Image": "http://164.100.47.132/mpimage/photo/4.jpg",
    "Positions Held": "\n1980\nElected to 7th Lok Sabha\n\n1981\nMember, Secretariat, Communist Party of India (Marxist) [C.P.I.(M)], Distt. Committee, Purulia\nMember, State Committee, C.P.I.(M), West Bengal\n\n1984\nRe-elected to 8th Lok Sabha (2nd term)\n\n1985 onwards\nMember, State Committee, C.P.I.(M), West Bengal\n\n1985-89\nMember, Railway Convention Committee\n\n1986-89\nMember, Joint Select Committee on Railways Bill\n\n1989\nRe-elected to 9th Lok Sabha (3rd term)\n\n1990-91\nChairman, Committee on Public Undertakings\nMember, House Committee\nMember, General Purposes Committee\n\n1990-96\nMember, Consultative Committee, Ministry of Railways\n\n1991\nRe-elected to 10th Lok Sabha (4th term)\n\n1993-95\nMember, Committee on Public Undertakings\n\n1993-96\nChairman, Committee on Government Assurances\nMember, Committee on Railways\n\n1996\nRe-elected to 11th Lok Sabha (5th term)\n\n1996-97\nChairman, Committee on Railways\nMember, Panel of Chairman\nMember, Consultative Committee, Ministry of Industry\n\n1998\nRe-elected to 12th Lok Sabha (6th term)\nMember, Panel of Chairman, Lok Sabha\n\n1998-99\nConvenor, Sub-Committee on Power, Committee on Energy\nMember, Committee on Energy and its Sub-Committees on Coal and Action Taken Reports\nMember, Committee of Privileges\nMember, General Purposes Committee\nMember, Committee to review the rate of dividend payable by the Railway Undertakings to General Revenues\nMember, Consultative Committee, Ministry of Industry\nSpecial Invitee, Consultative Committee, Ministry of Railways\n\n1999\nRe-elected to 13th Lok Sabha (7th term)\n\n1999-2004\nChairman, Committee on Petitions\nMember, Committee on Energy\nMember, General Purposes Committee\n\n2000-2004\nMember, Consultative Committee Ministry of Railways Special Member, Consultative Committee, Ministry of Industry\n\n2004\nRe-elected to 14th Lok Sabha( 8th term) Leader, CPI (M) Parliamentary Party, Lok Sabha Chairman, Committee on Railways Member, Rules Committee Member, General Purposes Committee\nMember, Committee on Security in Parliament Complex\nMember, Committee on Installation of Portraits/Statues of National Leaders and Parliamentarian in Parliament House\nMember, Consultative Committee, Ministry of Heavy Industry & Public Enterprises\nSpecial Member, Consultative Committee, Ministry of Power\n\n5 Aug. 2007\nChairman, Committee on Railways\n\n2008\nMember, Central Committee CPI (M)\n\n2009\nRe-elected to 15th Lok Sabha (9th term)\nLeader, CPI (M) Parliamentary Party, Lok Sabha\n\nJun. 2009\nMember, Panel of Chairmen, Lok Sabha\n\n29 Jun. 2009\nMember, Business Advisory Committee\n\n31 Aug. 2009\nChairman, Committee on Agriculture\n\n19 Oct. 2009\nMember, General Purposes Committee\nMember, Consultative Committee, Ministry of Heavy Industries & Public Enterprises\nSpecial Member, Consultative Committee, Ministry of Power\n",
    "Permanent Address": "\nVillage Kantaranguni, P.O. Adra,\nDistt. Purulia - 723 101, West Bengal\nTels.(03251) 244262,244900 Fax. (03252) 222360\n\n\n\n",
    "Father's Name": "Late Shri Kanailal Acharia",
    "Spouse's Name": "Smt. Rajlaxmi Acharia"
	}
	*/
	public function persist_candidates(){
	   $MP_JSONS_DIR = 'TODO: MP JSON FILES DIRECTORY';
	   
	   if ($handle = opendir($MP_JSONS_DIR)) {
        while (false !== ($mpjsonfilename = readdir($handle))) {
			$ext = pathinfo($mpjsonfilename, PATHINFO_EXTENSION);
            if ($mpjsonfilename != "." && $mpjsonfilename != ".." && $ext == "json") {
				$file = file_get_contents($MP_JSONS_DIR."/".$mpjsonfilename, FILE_USE_INCLUDE_PATH);
				$json_arry = json_decode($file,true);
				/**
				* TODO: 
				* For some MPs mother's name is missing. Need to handle that.
				* Also, need to check for all the fields. Index not found checks to be added.
				*/				
				print $json_arry["Place of Birth"] . " " . $json_arry["Mother's Name"] . "\n";
				//TODO: Save to Database pending. Print statement is temporary and to be removed after persistent logic is added.
            }
        }
        closedir($handle);
		}
	}
}