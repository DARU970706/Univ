#pragma once
#include <Windows.h>
#include <string>
#include <iostream>
#include <sstream>
#include "work.h"

namespace c___project {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for MyForm
	/// </summary>
	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}
	public: System::Windows::Forms::ImageList^  cardImage;
	protected:
	public: System::Windows::Forms::Button^  button1;
	private: System::ComponentModel::IContainer^  components;
	public:

	public: System::Windows::Forms::Label^  label1;
	public: System::Windows::Forms::Timer^  timer1;
	public: System::Windows::Forms::TextBox^  textBox1;
	public: System::Windows::Forms::Label^  label2;
	public: System::Windows::Forms::Button^  button2;
	public: System::Windows::Forms::PictureBox^  nullPic;
	public: System::Windows::Forms::Label^  label3;
	public: System::Windows::Forms::Label^  label4;
	public: System::Windows::Forms::ListBox^  listBox1;
	public: System::Windows::Forms::Label^  label5;

	public: array< System::Windows::Forms::PictureBox^ >^ BoxField;
	public: System::Windows::Forms::Button^  button3;
	public: System::Windows::Forms::Button^  button4;
	public: array< System::Windows::Forms::PictureBox^ >^ images;


#pragma region Windows Form Designer generated code

			void InitializeComponent(void)
			{
				this->components = (gcnew System::ComponentModel::Container());
				this->cardImage = (gcnew System::Windows::Forms::ImageList(this->components));
				this->button1 = (gcnew System::Windows::Forms::Button());
				this->label1 = (gcnew System::Windows::Forms::Label());
				this->timer1 = (gcnew System::Windows::Forms::Timer(this->components));
				this->textBox1 = (gcnew System::Windows::Forms::TextBox());
				this->label2 = (gcnew System::Windows::Forms::Label());
				this->button2 = (gcnew System::Windows::Forms::Button());
				this->nullPic = (gcnew System::Windows::Forms::PictureBox());
				this->label3 = (gcnew System::Windows::Forms::Label());
				this->label4 = (gcnew System::Windows::Forms::Label());
				this->listBox1 = (gcnew System::Windows::Forms::ListBox());
				this->label5 = (gcnew System::Windows::Forms::Label());
				this->button3 = (gcnew System::Windows::Forms::Button());
				this->button4 = (gcnew System::Windows::Forms::Button());
				(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nullPic))->BeginInit();
				this->SuspendLayout();
				// 
				// cardImage
				// 
				this->cardImage->ColorDepth = System::Windows::Forms::ColorDepth::Depth8Bit;
				this->cardImage->ImageSize = System::Drawing::Size(16, 16);
				this->cardImage->TransparentColor = System::Drawing::Color::Transparent;
				// 
				// button1
				// 
				this->button1->Location = System::Drawing::Point(694, 12);
				this->button1->Name = L"button1";
				this->button1->Size = System::Drawing::Size(101, 23);
				this->button1->TabIndex = 12;
				this->button1->Text = L"셋팅";
				this->button1->UseVisualStyleBackColor = true;
				this->button1->Click += gcnew System::EventHandler(this, &MyForm::button1_Click);
				// 
				// label1
				// 
				this->label1->AutoSize = true;
				this->label1->Location = System::Drawing::Point(711, 74);
				this->label1->Name = L"label1";
				this->label1->Size = System::Drawing::Size(63, 12);
				this->label1->TabIndex = 14;
				this->label1->Text = L"simulation";
				// 
				// timer1
				// 
				this->timer1->Interval = 50;
				this->timer1->Tick += gcnew System::EventHandler(this, &MyForm::timer1_Tick);
				// 
				// textBox1
				// 
				this->textBox1->Location = System::Drawing::Point(743, 89);
				this->textBox1->Name = L"textBox1";
				this->textBox1->Size = System::Drawing::Size(52, 21);
				this->textBox1->TabIndex = 15;
				// 
				// label2
				// 
				this->label2->AutoSize = true;
				this->label2->Location = System::Drawing::Point(692, 92);
				this->label2->Name = L"label2";
				this->label2->Size = System::Drawing::Size(45, 12);
				this->label2->TabIndex = 16;
				this->label2->Text = L"Interval";
				// 
				// button2
				// 
				this->button2->Location = System::Drawing::Point(694, 145);
				this->button2->Name = L"button2";
				this->button2->Size = System::Drawing::Size(101, 23);
				this->button2->TabIndex = 17;
				this->button2->Text = L"결과만 보기";
				this->button2->UseVisualStyleBackColor = true;
				this->button2->Click += gcnew System::EventHandler(this, &MyForm::button2_Click);
				// 
				// nullPic
				// 
				this->nullPic->Location = System::Drawing::Point(737, 385);
				this->nullPic->Name = L"nullPic";
				this->nullPic->Size = System::Drawing::Size(100, 50);
				this->nullPic->TabIndex = 51;
				this->nullPic->TabStop = false;
				// 
				// label3
				// 
				this->label3->AutoSize = true;
				this->label3->Location = System::Drawing::Point(692, 171);
				this->label3->Name = L"label3";
				this->label3->Size = System::Drawing::Size(40, 12);
				this->label3->TabIndex = 52;
				this->label3->Text = L"point :";
				// 
				// label4
				// 
				this->label4->Location = System::Drawing::Point(735, 171);
				this->label4->Name = L"label4";
				this->label4->Size = System::Drawing::Size(60, 12);
				this->label4->TabIndex = 53;
				this->label4->Text = L"0";
				this->label4->TextAlign = System::Drawing::ContentAlignment::TopRight;
				// 
				// listBox1
				// 
				this->listBox1->Font = (gcnew System::Drawing::Font(L"굴림", 8.25F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
					static_cast<System::Byte>(129)));
				this->listBox1->FormattingEnabled = true;
				this->listBox1->ItemHeight = 11;
				this->listBox1->Location = System::Drawing::Point(12, 24);
				this->listBox1->Name = L"listBox1";
				this->listBox1->Size = System::Drawing::Size(211, 455);
				this->listBox1->TabIndex = 54;
				// 
				// label5
				// 
				this->label5->AutoSize = true;
				this->label5->Location = System::Drawing::Point(12, 9);
				this->label5->Name = L"label5";
				this->label5->Size = System::Drawing::Size(28, 12);
				this->label5->TabIndex = 55;
				this->label5->Text = L"task";
				// 
				// button3
				// 
				this->button3->Location = System::Drawing::Point(694, 41);
				this->button3->Name = L"button3";
				this->button3->Size = System::Drawing::Size(101, 23);
				this->button3->TabIndex = 56;
				this->button3->Text = L"한단계씩 실행";
				this->button3->UseVisualStyleBackColor = true;
				this->button3->Click += gcnew System::EventHandler(this, &MyForm::button3_Click);
				// 
				// button4
				// 
				this->button4->Location = System::Drawing::Point(694, 116);
				this->button4->Name = L"button4";
				this->button4->Size = System::Drawing::Size(101, 23);
				this->button4->TabIndex = 57;
				this->button4->Text = L"자동 실행";
				this->button4->UseVisualStyleBackColor = true;
				this->button4->Click += gcnew System::EventHandler(this, &MyForm::button4_Click);
				// 
				// MyForm
				// 
				this->AutoScaleDimensions = System::Drawing::SizeF(7, 12);
				this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
				this->BackColor = System::Drawing::SystemColors::ButtonHighlight;
				this->ClientSize = System::Drawing::Size(807, 488);
				this->Controls->Add(this->button4);
				this->Controls->Add(this->button3);
				this->Controls->Add(this->label5);
				this->Controls->Add(this->listBox1);
				this->Controls->Add(this->label4);
				this->Controls->Add(this->label3);
				this->Controls->Add(this->nullPic);
				this->Controls->Add(this->button2);
				this->Controls->Add(this->label2);
				this->Controls->Add(this->textBox1);
				this->Controls->Add(this->label1);
				this->Controls->Add(this->button1);
				this->FormBorderStyle = System::Windows::Forms::FormBorderStyle::FixedSingle;
				this->MaximizeBox = false;
				this->Name = L"MyForm";
				this->StartPosition = System::Windows::Forms::FormStartPosition::CenterScreen;
				this->Text = L"자료구조 프로젝트";
				this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
				(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->nullPic))->EndInit();
				this->ResumeLayout(false);
				this->PerformLayout();

			}
#pragma endregion

			std::string TCHARToString(const TCHAR* ptsz)
			{
				int len = wcslen((wchar_t*)ptsz);
				char* psz = new char[2 * len + 1];
				wcstombs(psz, (wchar_t*)ptsz, 2 * len + 1);
				std::string s = psz;
				delete[] psz;
				return s;
			}

			void set_Picture()
			{

				label4->Text = point.ToString();

				if (stp != -1)
				{
					BoxField[1]->Image = Image::FromFile(paths(std::to_string(pe[peTop]) + ".png"));
				}
			
				for (int i = 0; i < 4; i++)
				{
					if (pr[i]!=0) BoxField[i+2]->Image = images[pr[i]+i*13]->Image;
					else BoxField[i + 2]->Image = images[0]->Image;
				}

				for (int i = 0; i < 7; i++)
				{
					for (int j = 0; j <= top[i]-st[i]; j++)
					{
						BoxField[6 + (20 * i) + j]->Visible = true;
						if (visible[20 * i + j] == 1&& stack[20 * i + j]!=-1) BoxField[6 + (20 * i) + j]->Image = images[stack[20 * i + j]]->Image;
						else BoxField[6 + (20 * i) + j]->Image = images[0]->Image;
					}
					for (int j = top[i] -st[i] + 1; j < 20; j++)
					{
						BoxField[6 + (20 * i) + j]->Visible = false;
					}
				}
			}

			void printIndex(int n,String^ b)
			{
				listBox1->Items->Add(name[(int)((n - 1) / 13)] + " " + cardName[(cardCode(n) - 1)] + b);
			}

			private: System::Void button1_Click(System::Object^  sender, System::EventArgs^  e) {
				outc = 0;
				setting();
				pe[0] = stockPop();
				listBox1->Items->Clear();
				listBox1->Items->Add("게임시작");
				printIndex(pe[0], "를 폐물파일로 이동했습니다.");
				set_Picture();
			}

			private: System::Void timer1_Tick(System::Object^  sender, System::EventArgs^  e) {
				doOne();
				if (gamef == 1) timer1->Enabled = false;
			}

			String^ paths(std::string n)
			{
				TCHAR programpath[_MAX_PATH];
				GetCurrentDirectory(_MAX_PATH, programpath);
				std::string a = TCHARToString(programpath) + "\\card\\" + n;
				String^ k = gcnew String(a.c_str());
				return k;
			}

			public:void createCard(int i, int x, int y, int n)
			{
				//
				this->BoxField[i] = gcnew System::Windows::Forms::PictureBox();
				this->BoxField[i]->Name = "Box";
				this->BoxField[i]->Image = images[n]->Image;
				this->BoxField[i]->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
				this->BoxField[i]->Location = System::Drawing::Point(x, y);
				this->BoxField[i]->TabIndex = 0;
				this->BoxField[i]->TabStop = false;
				this->BoxField[i]->Size = System::Drawing::Size(56, 85);
				this->BoxField[i]->SizeMode = System::Windows::Forms::PictureBoxSizeMode::Zoom;
				this->Controls->Add(BoxField[i]);
			}


			private: System::Void MyForm_Load(System::Object^  sender, System::EventArgs^  e) {

				int i;
				int n;

				this->BoxField = gcnew array< PictureBox^ >(400);
				this->images = gcnew array< PictureBox^ >(400);

				for (i = 0; i <= 52; i++)
				{
					this->images[i] = gcnew System::Windows::Forms::PictureBox();
					this->images[i]->Image = Image::FromFile(paths(std::to_string(i) + ".png"));
				}

				//폐물
				createCard(0, 22 + 221, 23, 0);
				createCard(1, 22 + 221+62, 23, 0);
				//폐물

				//출력
				for (i = 0; i < 4; i++)
				{
					createCard(i + 2, 208 + (i * 62) + 221, 23, 0);
				}
				//출력

				//패
				listBox1->Items->Add("c++ 프로젝트");
				n = 6;
				for (int k = 0; k < 7; k++)
				{
					for (i = 19; i >= 0; i--)
					{
						createCard(i + n, 22 + (62 * k) + 221, 134 + (i * 15), 0);
					}
					n = n + 20;
				}

				//패
			}

	void doOne()
	{
		if (gamef == 0)
		{
			int t;

			t = a();

			if (t != 0)
			{
				outc = 0;
				set_Picture();
				printIndex(t, "를 출력에 이동하였습니다.(A1)");
				point = point + 5;
				return;
			}

			t = p();
			if (t != 0)
			{
				outc = 0;
				set_Picture();
				printIndex(t, "를 스톡에서 폐물로 옮겼습니다.(A2)");
				return;
			}

			t = b();
			if (t != 0)
			{
				outc = 0;
				outc = 0;
				set_Picture();
				printIndex(t, "를 스톡에서 패로 옮겼습니다.(B)");
				return;
			}

			t = c();
			if (t != 0)
			{
				outc = 0;
				for (int i = 0; i < 7; i++)
				{
					visible[top[i]] = 1;
				}
				set_Picture();
				printIndex(t, "를 이동하였습니다.(C)");
				return;
			}

			t = ps();
			if (t != 0 && t != -1)
			{
				outc = 0;
				set_Picture();
				printIndex(t, "를 스톡에서 폐물로 옮겼습니다.(E)");
				return;
			}
			if (t == -1) outc++;

			if (outc == 5)
			{
				gamef = 1;
				listBox1->Items->Add("게임종료");
			}
		}
	}
	private: System::Void button3_Click(System::Object^  sender, System::EventArgs^  e) {

		doOne();

	}
private: System::Void button4_Click(System::Object^  sender, System::EventArgs^  e) {
	outc = 0;
	setting();
	pe[0] = stockPop();
	listBox1->Items->Clear();
	listBox1->Items->Add("게임시작");
	printIndex(pe[0], "를 폐물파일로 이동했습니다.");
	set_Picture();

	String^a = textBox1->Text;
	int n= System::Int32::Parse(a);
	timer1->Interval = n;
	timer1->Enabled = true;
}
private: System::Void button2_Click(System::Object^  sender, System::EventArgs^  e) {
	
	outc = 0;
	setting();
	pe[0] = stockPop();
	listBox1->Items->Clear();
	listBox1->Items->Add("게임시작");
	printIndex(pe[0], "를 폐물파일로 이동했습니다.");
	set_Picture();

	while (true)
	{
		doOne();
		if (gamef == 1) break;
	}
}
};
}
