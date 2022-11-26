PGDMP         :            
    z            sistema    15.1    15.1 5    J           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            K           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            L           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            M           1262    16521    sistema    DATABASE     ~   CREATE DATABASE sistema WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE sistema;
                postgres    false            �            1259    16522    alunos    TABLE     �  CREATE TABLE public.alunos (
    codigo_aluno integer NOT NULL,
    aluno text NOT NULL,
    data_nascimento date,
    sexo character(1),
    telefone text,
    celular text,
    email text,
    observacao text,
    endereco text,
    numero text,
    complemento text,
    bairro text,
    cidade text,
    estado character(2),
    pais text,
    cep text,
    cpf text NOT NULL,
    CONSTRAINT alunos_sexo CHECK ((sexo = ANY (ARRAY['M'::bpchar, 'F'::bpchar])))
);
    DROP TABLE public.alunos;
       public         heap    postgres    false            �            1259    16528    alunos_codigo_aluno_seq    SEQUENCE     �   CREATE SEQUENCE public.alunos_codigo_aluno_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.alunos_codigo_aluno_seq;
       public          postgres    false    214            N           0    0    alunos_codigo_aluno_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.alunos_codigo_aluno_seq OWNED BY public.alunos.codigo_aluno;
          public          postgres    false    215            �            1259    16529    assiduidade    TABLE     �   CREATE TABLE public.assiduidade (
    codigo_matricula integer NOT NULL,
    data_entrada timestamp without time zone DEFAULT LOCALTIMESTAMP NOT NULL
);
    DROP TABLE public.assiduidade;
       public         heap    postgres    false            �            1259    16533    cidades    TABLE     t   CREATE TABLE public.cidades (
    cidade text NOT NULL,
    estado character(2) NOT NULL,
    pais text NOT NULL
);
    DROP TABLE public.cidades;
       public         heap    postgres    false            �            1259    16538    faturas_matriculas    TABLE     �   CREATE TABLE public.faturas_matriculas (
    codigo_matricula integer NOT NULL,
    data_vencimento date NOT NULL,
    valor numeric(9,2) NOT NULL,
    data_pagamento timestamp without time zone,
    data_cancelamento date
);
 &   DROP TABLE public.faturas_matriculas;
       public         heap    postgres    false            �            1259    16541 
   graduacoes    TABLE     ^   CREATE TABLE public.graduacoes (
    modalidade text NOT NULL,
    graduacao text NOT NULL
);
    DROP TABLE public.graduacoes;
       public         heap    postgres    false            �            1259    16546 
   matriculas    TABLE     �   CREATE TABLE public.matriculas (
    codigo_matricula integer NOT NULL,
    codigo_aluno integer NOT NULL,
    data_matricula date NOT NULL,
    dia_vencimento integer NOT NULL,
    data_encerramento date
);
    DROP TABLE public.matriculas;
       public         heap    postgres    false            �            1259    16549    matriculas_codigo_matricula_seq    SEQUENCE     �   CREATE SEQUENCE public.matriculas_codigo_matricula_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.matriculas_codigo_matricula_seq;
       public          postgres    false    220            O           0    0    matriculas_codigo_matricula_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.matriculas_codigo_matricula_seq OWNED BY public.matriculas.codigo_matricula;
          public          postgres    false    221            �            1259    16550    matriculas_modalidades    TABLE     �   CREATE TABLE public.matriculas_modalidades (
    codigo_matricula integer NOT NULL,
    modalidade text NOT NULL,
    graduacao text,
    plano text NOT NULL,
    data_inicio date DEFAULT CURRENT_DATE,
    data_fim date
);
 *   DROP TABLE public.matriculas_modalidades;
       public         heap    postgres    false            �            1259    16556    modalidades    TABLE     B   CREATE TABLE public.modalidades (
    modalidade text NOT NULL
);
    DROP TABLE public.modalidades;
       public         heap    postgres    false            �            1259    16561    planos    TABLE     ~   CREATE TABLE public.planos (
    modalidade text NOT NULL,
    plano text NOT NULL,
    valor_mensal numeric(9,2) NOT NULL
);
    DROP TABLE public.planos;
       public         heap    postgres    false            �            1259    16566    usuarios    TABLE     o   CREATE TABLE public.usuarios (
    usuario text NOT NULL,
    perfil text NOT NULL,
    senha text NOT NULL
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false            �           2604    16571    alunos codigo_aluno    DEFAULT     z   ALTER TABLE ONLY public.alunos ALTER COLUMN codigo_aluno SET DEFAULT nextval('public.alunos_codigo_aluno_seq'::regclass);
 B   ALTER TABLE public.alunos ALTER COLUMN codigo_aluno DROP DEFAULT;
       public          postgres    false    215    214            �           2604    16572    matriculas codigo_matricula    DEFAULT     �   ALTER TABLE ONLY public.matriculas ALTER COLUMN codigo_matricula SET DEFAULT nextval('public.matriculas_codigo_matricula_seq'::regclass);
 J   ALTER TABLE public.matriculas ALTER COLUMN codigo_matricula DROP DEFAULT;
       public          postgres    false    221    220            <          0    16522    alunos 
   TABLE DATA           �   COPY public.alunos (codigo_aluno, aluno, data_nascimento, sexo, telefone, celular, email, observacao, endereco, numero, complemento, bairro, cidade, estado, pais, cep, cpf) FROM stdin;
    public          postgres    false    214   �B       >          0    16529    assiduidade 
   TABLE DATA           E   COPY public.assiduidade (codigo_matricula, data_entrada) FROM stdin;
    public          postgres    false    216   �C       ?          0    16533    cidades 
   TABLE DATA           7   COPY public.cidades (cidade, estado, pais) FROM stdin;
    public          postgres    false    217   �C       @          0    16538    faturas_matriculas 
   TABLE DATA           y   COPY public.faturas_matriculas (codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento) FROM stdin;
    public          postgres    false    218   9
      A          0    16541 
   graduacoes 
   TABLE DATA           ;   COPY public.graduacoes (modalidade, graduacao) FROM stdin;
    public          postgres    false    219   �
      B          0    16546 
   matriculas 
   TABLE DATA           w   COPY public.matriculas (codigo_matricula, codigo_aluno, data_matricula, dia_vencimento, data_encerramento) FROM stdin;
    public          postgres    false    220   �
      D          0    16550    matriculas_modalidades 
   TABLE DATA           w   COPY public.matriculas_modalidades (codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim) FROM stdin;
    public          postgres    false    222         E          0    16556    modalidades 
   TABLE DATA           1   COPY public.modalidades (modalidade) FROM stdin;
    public          postgres    false    223   e      F          0    16561    planos 
   TABLE DATA           A   COPY public.planos (modalidade, plano, valor_mensal) FROM stdin;
    public          postgres    false    224   �      G          0    16566    usuarios 
   TABLE DATA           :   COPY public.usuarios (usuario, perfil, senha) FROM stdin;
    public          postgres    false    225         P           0    0    alunos_codigo_aluno_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.alunos_codigo_aluno_seq', 7, true);
          public          postgres    false    215            Q           0    0    matriculas_codigo_matricula_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.matriculas_codigo_matricula_seq', 6, true);
          public          postgres    false    221            �           2606    16574    alunos alunos_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pk PRIMARY KEY (codigo_aluno);
 :   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_pk;
       public            postgres    false    214            �           2606    16576    assiduidade assiduidade_pk 
   CONSTRAINT     t   ALTER TABLE ONLY public.assiduidade
    ADD CONSTRAINT assiduidade_pk PRIMARY KEY (codigo_matricula, data_entrada);
 D   ALTER TABLE ONLY public.assiduidade DROP CONSTRAINT assiduidade_pk;
       public            postgres    false    216    216            �           2606    16578    cidades cidades_pk 
   CONSTRAINT     b   ALTER TABLE ONLY public.cidades
    ADD CONSTRAINT cidades_pk PRIMARY KEY (cidade, estado, pais);
 <   ALTER TABLE ONLY public.cidades DROP CONSTRAINT cidades_pk;
       public            postgres    false    217    217    217            �           2606    16580 (   faturas_matriculas faturas_matriculas_pk 
   CONSTRAINT     �   ALTER TABLE ONLY public.faturas_matriculas
    ADD CONSTRAINT faturas_matriculas_pk PRIMARY KEY (codigo_matricula, data_vencimento);
 R   ALTER TABLE ONLY public.faturas_matriculas DROP CONSTRAINT faturas_matriculas_pk;
       public            postgres    false    218    218            �           2606    16582    graduacoes graduacoes_pk 
   CONSTRAINT     i   ALTER TABLE ONLY public.graduacoes
    ADD CONSTRAINT graduacoes_pk PRIMARY KEY (modalidade, graduacao);
 B   ALTER TABLE ONLY public.graduacoes DROP CONSTRAINT graduacoes_pk;
       public            postgres    false    219    219            �           2606    16584 0   matriculas_modalidades matriculas_modalidades_pk 
   CONSTRAINT     �   ALTER TABLE ONLY public.matriculas_modalidades
    ADD CONSTRAINT matriculas_modalidades_pk PRIMARY KEY (codigo_matricula, modalidade);
 Z   ALTER TABLE ONLY public.matriculas_modalidades DROP CONSTRAINT matriculas_modalidades_pk;
       public            postgres    false    222    222            �           2606    16586    matriculas matriculas_pk 
   CONSTRAINT     d   ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT matriculas_pk PRIMARY KEY (codigo_matricula);
 B   ALTER TABLE ONLY public.matriculas DROP CONSTRAINT matriculas_pk;
       public            postgres    false    220            �           2606    16588    modalidades modalidades_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.modalidades
    ADD CONSTRAINT modalidades_pk PRIMARY KEY (modalidade);
 D   ALTER TABLE ONLY public.modalidades DROP CONSTRAINT modalidades_pk;
       public            postgres    false    223            �           2606    16590    planos planos_pk 
   CONSTRAINT     ]   ALTER TABLE ONLY public.planos
    ADD CONSTRAINT planos_pk PRIMARY KEY (modalidade, plano);
 :   ALTER TABLE ONLY public.planos DROP CONSTRAINT planos_pk;
       public            postgres    false    224    224            �           2606    16592    usuarios usuarios_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pk PRIMARY KEY (usuario);
 >   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pk;
       public            postgres    false    225            �           1259    16593    alunos_1    INDEX     <   CREATE INDEX alunos_1 ON public.alunos USING btree (aluno);
    DROP INDEX public.alunos_1;
       public            postgres    false    214            �           2606    16594    alunos alunos_enderecos_f2    FK CONSTRAINT     �   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_enderecos_f2 FOREIGN KEY (cidade, estado, pais) REFERENCES public.cidades(cidade, estado, pais) DEFERRABLE;
 D   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_enderecos_f2;
       public          postgres    false    217    214    214    214    3221    217    217            �           2606    16599    assiduidade assiduidade_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.assiduidade
    ADD CONSTRAINT assiduidade_f1 FOREIGN KEY (codigo_matricula) REFERENCES public.matriculas(codigo_matricula) DEFERRABLE;
 D   ALTER TABLE ONLY public.assiduidade DROP CONSTRAINT assiduidade_f1;
       public          postgres    false    3227    220    216            �           2606    16604 (   faturas_matriculas faturas_matriculas_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.faturas_matriculas
    ADD CONSTRAINT faturas_matriculas_f1 FOREIGN KEY (codigo_matricula) REFERENCES public.matriculas(codigo_matricula) DEFERRABLE;
 R   ALTER TABLE ONLY public.faturas_matriculas DROP CONSTRAINT faturas_matriculas_f1;
       public          postgres    false    218    220    3227            �           2606    16609    graduacoes graduacoes_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.graduacoes
    ADD CONSTRAINT graduacoes_f1 FOREIGN KEY (modalidade) REFERENCES public.modalidades(modalidade) DEFERRABLE;
 B   ALTER TABLE ONLY public.graduacoes DROP CONSTRAINT graduacoes_f1;
       public          postgres    false    223    3231    219            �           2606    16614    matriculas matriculas_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculas
    ADD CONSTRAINT matriculas_f1 FOREIGN KEY (codigo_aluno) REFERENCES public.alunos(codigo_aluno) DEFERRABLE;
 B   ALTER TABLE ONLY public.matriculas DROP CONSTRAINT matriculas_f1;
       public          postgres    false    3217    214    220            �           2606    16619 0   matriculas_modalidades matriculas_modalidades_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculas_modalidades
    ADD CONSTRAINT matriculas_modalidades_f1 FOREIGN KEY (codigo_matricula) REFERENCES public.matriculas(codigo_matricula) DEFERRABLE;
 Z   ALTER TABLE ONLY public.matriculas_modalidades DROP CONSTRAINT matriculas_modalidades_f1;
       public          postgres    false    3227    222    220            �           2606    16624 0   matriculas_modalidades matriculas_modalidades_f2    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculas_modalidades
    ADD CONSTRAINT matriculas_modalidades_f2 FOREIGN KEY (modalidade) REFERENCES public.modalidades(modalidade) DEFERRABLE;
 Z   ALTER TABLE ONLY public.matriculas_modalidades DROP CONSTRAINT matriculas_modalidades_f2;
       public          postgres    false    222    223    3231            �           2606    16629 0   matriculas_modalidades matriculas_modalidades_f3    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculas_modalidades
    ADD CONSTRAINT matriculas_modalidades_f3 FOREIGN KEY (modalidade, graduacao) REFERENCES public.graduacoes(modalidade, graduacao) DEFERRABLE;
 Z   ALTER TABLE ONLY public.matriculas_modalidades DROP CONSTRAINT matriculas_modalidades_f3;
       public          postgres    false    222    219    219    3225    222            �           2606    16634 0   matriculas_modalidades matriculas_modalidades_f4    FK CONSTRAINT     �   ALTER TABLE ONLY public.matriculas_modalidades
    ADD CONSTRAINT matriculas_modalidades_f4 FOREIGN KEY (modalidade, plano) REFERENCES public.planos(modalidade, plano) DEFERRABLE;
 Z   ALTER TABLE ONLY public.matriculas_modalidades DROP CONSTRAINT matriculas_modalidades_f4;
       public          postgres    false    224    222    222    224    3233            �           2606    16639    planos planos_f1    FK CONSTRAINT     �   ALTER TABLE ONLY public.planos
    ADD CONSTRAINT planos_f1 FOREIGN KEY (modalidade) REFERENCES public.modalidades(modalidade) DEFERRABLE;
 :   ALTER TABLE ONLY public.planos DROP CONSTRAINT planos_f1;
       public          postgres    false    224    223    3231            <   �   x���AK�@�ϳ�b��23�9�Q��(��&�!�V(d�K�;�xR|�����6]ҡ�A��g����W"J�7� ��Q�����J�
۳b��Mײ;�C�v]�ߪ%�JX���K�b��nasn4�S7���+m��A<1T����$of�gX���:Bp�g�Ѝ��3��%/���oKؔz!�����^'>�P��(CBJ�f���V#����آ���)��k��7�xa8      >   )   x�3�4200�54�54T00�#.3���nQ#$�=... µI      ?      x���M���&6�������Nw2T��j��m��V�u׬�Π������d���78�ll?T�CI�c�nk��
���?����������ǚ�%n�0��([��m
��ƴCx���,)Oa}(��P [����Ia��v��aK��+������vK�	w�fX�/�`�=�m+��qI{R����~�������G��f��mLS@4��V�;��5�a��ߡLa/�S���^�^�oC��hy��-kt�?ø�U��u�<��O��m2��+=�̄� ��n[ȷ/�O��VHLz��3'P�DSc�4�E���@l�%��-�"�kHK�@�{������B��2#�w��5#6�]���a��K�7���*K�w��[\� s������i���3<�XkI��u�\d��;KBB���47R��xm�ҥ���Դ�Y����V}u�^�G4d�T��=,q�ou����
��y�58� ��#�>--a�S�Dz��$fZ�ּ�HBk���Ɓ~�y-Y�
�7-z��4<��y>�u�a�	���YF�.�'Mp͋x����6��{�����%>�++e v��j�۾�y��i�yr������V�4Q�]h��pM�^=xK����Cn/�u���}����Y��qIb��z�����O�p{ҷ=�6f\���t�3����1�z6�@,���"<�3!t{�u��7����[ڒ��aF�n�+w����x�ȟ�k���l�*�p3z;Y8�迻��Sz0/�n�?<���ց�t<	���%�0���
sx\�a�2�i�=���E���-u$O�=o{aO�ѡ�%"B�'���5�g�f�����Y�q�w �V����=>ycS�����.�i�i㢅m���5��Pg��c�5��)h-�Ŀ�f�~�Q��;���~eEdhϖ�*c�g"N���F�6��W��ĳ����~ ��7�J���;���!|�x1mR��ļ��@�r�c�p�]hoYָH,@����	/M;눿�yF<	!M+�O|�m/�f�I��U�@��jaJĂ�h�d�gf8n��ˮP���6м���l>��:A�xs�b���6����v�*����@2����H;��S#<��������Bg���똟61����o�\ק�e-RK�l��=�[���P��/؛�n�t����"���eA[�er\E@ �g���&���a�	�0�.bm�K18�C������$�����wa��E��K���l2�*BN��z�k:�w���#��!������2�a=2<�'�[�@%�w��o�n��*��D��1�F�ʫ
Yi�'��&��)U��.�'i�B�Y.�� ֲ":�u�w��HcU�"���<�P�M粇)� ��
�U"B�	��z��A��� �XX�᦬?���w�$R7��1�o�]ӈm�V�с��5�w��
�Ά6�'ͼ4/���7B��,���*�����J�Fw�H4(�z��'g�k� ���(�JECoK�"z�nu'ƶ�C|����^�G�mb�1�c�`�M�;|8)|�eM8�!����δy�y:�z�)͈_����`Ə���H��!ኤ^�r�m��Ō�P�K���
=g"�UƬ��E�� ���?CzgXdϲ�%���M��Hq,�j
���`�o�Z?O�6Wj�f���P�	D���͡^��4��m{��t�3Ћ�9C��a���ڑNT�uK��C��8,����&���
�(ӆ/�x�����z��8A�l �����c=�x0E� �W��/�H	�W���w�8%2��I��/�q�4^�vv�@�P���]7=H;"�����	夯��'R,��-��]���X���b�qS�sk�@���)��������N�,V�H�3ܑ�TC��K��i��������5i��0����aw� ���DY(+�4_j^ȝ'�,��*r���tHĨ�	���?���C�RO�����7��W2�܏fۡ���{��W��V;q_��9��f���vY�S�a�%ޮ��Dε���3�4�^`J���vӐ�>�&�����S��Ҁ���E���4#��dD�՛��Ь~o�HeهT<����$�����UU����EOv7V@ݍPw�л����"\�񉝻񅜻�	���������meR�]�x�p�zLᢞL�����,r0����	�L�B�)\��N���1DgDzL����L�S�'S8!�N�f

4LAݨJP
8���������Zt�*��w;hr�0~g5cXwcv�񳍗.�j�uō���5u����!�\SދE'͈R��;��E>5�k^��U�V]79V��SCɱ
�U]r��Dn�ɭ�r��U�ܪ�*��@�[��
���[5S<wK��Ʈ��B�����BØ�����Rmm��6��n���s��0�[��c���s�T����l���l�1k6�4D촺�F �����f6R��l�`o6���l� g6�h�l��f6RP3)���N�0]@��rQ?�X�nؽw�/���BV\������ِP�>aP�ŵph��~vY.L��˞�+�ɻ�9D���댿X`�#1zc�E����Ք>�Rzf)��&XQ���+>dce>�?i���G��mH�i�"����x�^�Ϸ�%�H���|:Ev����d��}&Z��]K����ޤzi mt����4��#B+k
�;|A4q�ЃO��O����%>ғ�芏%	�<��b4�w�l�e�]�����a��*bΦ-IH�@{2�Y�@�� ���f��8��:$G�B[.����I��x}��';v�5Ympw�H��mC��I8�e����4�Ww�1�)����Gg��Y�� 963�7 Q��7��w�[u|#@\��'�!�	eq�����ŉ&n��������&@�Y1�=;��a�Ml�ȓ�k0�Y��yu�g^]i�� �=�}l^3�ăy�n��$ё���z��6|�%���v���h d!e�cG�5�fٶ�L�����U���y9��49_(�)�u��=�'^�|����7 �%�B�p��hG�|��ӑ\ݩ=^�E�E�nrh����0���5�W�#�1i��jg�ow��1�M�5�71�ߌ�J�����+��9S��!��V]u�:`�3�M���mǓ��E2�n�.�Kbv8H|ٕv�%���Z4|3�3��4�	�V@�_xcY�-�y�>�8P^�%�<��+��|G�1I�SD`
�XT�Rm��z�h�z�l�@�W��ū/B�]�~���1��U��^n����hp ��v`�	��HUbZ�l���o���|*���뇆Vk�x��/�]�T�x-�n�>�`��3����ʰW�0� $���	�v�'y!��5@�4$��J2��WZ,�0��*x
�%B��reb�=#Ac�~�͡a���Up^g�7����4������ͻr�E��1��n��b�[5NNf��G� �Sm�����̦˂�(;��Ezּ�.��`]MH��e�����O�0���H:��H�L��	랰b*b��@�닍(�q��W�ulBq�]iEwx��چ*���>i�o�E,�W��ÿz��<��̖��{��na"�����k���хQ�5m3K���i�
�lCqn��������x����I�������E4�Y�����a��PD_�0,��'�X� 	�ڌ�"=�3<9�W��V� ov���9e=��wE���kzڇ3Κ�-�\��'������������Zu�?܋�hZ���!����s���,4�F5�i��E�W��C�ħ|�D�Vk;p��j癩�⭙nݻ@�W܊}0��{|w�KCB���Lw�x&M��7��3���Kŋ�R���@c�:ʳ(��c��s؀-���8���{�芟�U��8 �ճgM�;��1Cq�+2H���j��,�:&@��j�t��a�t���*��i�{��.��hKi2�U)��6Z�S#� ���V��"}    �ڼ�Qy��:�_WE�(Qֻ�����r(F��F*+�C+�P�����Ft焂�m���+�� AW&2wsA&QI��J�'n�;q6w~�H`#dn9��Y������2�mi�0h[ۊ5R������u�w`�3b�o#�Ʈl���K*�� ����l�`o�1]��FҖ�f|�(H�>*6�fl�r J;!��2�9�L��!0�����1��e���$�Mlm@
�h;�	�`�|$(O��e�ٞ�
b"z���lK���y@%n/娀`f�$E�����rGe��%���04������cf0kEƕ_�^	@P�|Ӧ�Q����]34��u�8�d� ԀSxcE�+���b�D#�񮨻5��D-�m6g�1}~M�����X>
����)��B+{�q|�q����N���1�Kc4qCaMm�����MV�ÕS�h���	v� �&�aB�Hl��o���Q�1�В��|����l$<������5�K���1Q�j��`_(�1�Uq�d&� ,�����7@���g�cJA�D�VT�/� K�!~/�F�����ҭ7lCB8�ؖ3Il^��1��[~���{[�k��6BR�CA2%b����x��>�L�T����bf��[l��TX��$���&�]i�J�`��M�{4mhN�c:z�;��V�Q�	e�r(=�ɂ�CG�=cN�)��x��� f2�I�h��s�e��Z�.v�'�(^D��^Q��xR�Z��D���nT�*A��Z<>���;>L��$x��$M$�ŭGq��"	Vz���1|'�����j\�e{�S��0i�3��!�G�\:�٫���9��h#������9q��r�$ 8�u� ��Ol#
�hg�#󞙧Â,�A#�0$�v:�vļ�y/0
"�Lo�q⦅���lJ�Ƀ���f�����m��cm\�0vh=θ��n':���^+g����y��I�I��e3r�+�0��+��}5o$d��	���r_��K�ߖw�=���λ�ٸX=�`��;N�*�ky��@w�	�h!�u�A��'�R�̋Ql hO���X0�V� |.]�5v3P�˾ ͍���9�Y2~U�Lw�YZ�Ɖt; ^�YC^o�_�d=�o�	?�g��5h*�6�7�d�Y�uv�9JxRq��R��W^�'ᤚ>��g$ɪb�ƌe�!7Q9��w'�B��� ������H���|�� �[0�3���(�������p�c�{�3=�9��J&�tɀ�n>��bz�ڣy�6os�Ţ�$S#v�FۜG�4曯�5I�x�����nL���m�l�]�0�jtZ�PŌ�V��>o�,_@{SB~�i�(|��7�)n8�̠�yz�}�Ĉ�����A����k`�mţ�!���Q������C&ϡ��1��=�As�YȒ������I�q��c�qmo3�b]�<V����,q�*���և��9F# ���GZ���gcP�D�����ǧ풲�simw�8	Nkd�c:m�N�B�NB�
o�_�	�����9�茻HYQ�&����+���۬f���:�v6令XS m4ӌZ#��ƙ�)?����A��]�>-ʹe6���=!�\y�EKԷ��XKJ�&��0'�q��N֩?S����k5�⊶dâ$���Ր�6���>�czDQ3dd�� �[3�y�����k�q�c	���s��NlO�Hu���~�RP��p�Zu�u�3ܓ1.��a�>�[��}�Q���z�$^Q�9ŝ�~�*�d��;���α�s4F�g�%��Fޜڐ3I�S����Ԕ|�����uK�͓$��������C^��i���6"G0��T�'���׫��/���x%��8@~�G���LiDܘ����׸i�j<(BFg����iL�ew����9�du���#+��q��5{3�Z*�Q��>ae���v�"����iLzd�g��[�U�+���l�
6��t���)B@`MO�1TZ��Y�r}��'�>z�MD-ń
��f��ӌVVC�;�E1��F��l�\0�"qL�|�� �d�Mr�ɩ��Y�<sJ&B2����k�&4��u��J&_l����VǇ�f%�]�����%_�E޶ͷ��\5��Q�nnۏ\�z��#��k�Gp�r@�X
�`��U�gB��1bev�V�I�.����A��d�6e��1�ĳ���_Zˈy���4?��F���Utݺ��Vɷ0�~a�^;}:|w�q�	 ��@�}Y���jO��5���O�GB���I)\q
�,[�͖I[K�EV�d�� M��ZU����>�k�/�\6��g;V��&��'�*�u2�Z�]���.a_{E���T~�Lh�z_���������X �.��n�\�z�����=g�)\�XǢ�n�d{���oY���S�W�s������II��1�6d�c�
)r�mf�P�k��a��$�$Gđ�/��|&ҿ��`g���t:G�;�u �����x)/��%�Bp��fI���^5�o�b!�Hd��Y��MIf�@^����d�`vЩ�h&}�J0�w �$[[�5�<p44�/�@{�ɍ�f�@Nd���5$Y� �b-G�.�9�d�����o��\#-BUC���^1x�F�-��	���5+wpz����������E�b3����!G����	ؓGx�� ~�%3�֪�2s�%��ռ�!g��ꈰ�i$��A��dIN��8��v��~ðr��&AbM�q�T��~�iZ�w����G���)���܋N�G�،��I�H��Y���h&��l�(��۴��n�����(r���z�Kr��C�`�aȚ���^�}�����F�"Z���#	�|�������t��ι^4¢g�F�'�*c�*�5�`q��',���D��?,6�'�p �^'�μ>���(ʝ�-��_�߈�?�Vr��s�C�9�̿���`3[<"�L����(��j�����℩���g�'�]�A� ��~	�Ug��*�孝�kxa6�Q��'�Q�Ѧ��c�$.030���1N��|} un9�Լ��V�}t��� ;,�n7�s|��N;7�X�xa?���-x�v8*��=2jl
+��>
،"��z|��@	�I����aqLh�p��$�蛫���'|��VZj %I��N2������Is��q;`���L^A�[�k��s�b�%-�bָ=�[s �?�3dD~�����5�"��2����A�b�.g}{ �y���x;�T�Ҕ�����2�ʹ>ʃ)$O�1N�@S�1��D���\#`p[eA�<M���"ml�sFh�d�WLb�'v�C��C� �>��`�~D*|�� I�����zb�"éA�7ޡ7�X�ޚk���[�6��#�ء��D�z����O�2�[��Ҙ�d{�J#;�W[<�^��/e]_'�x�?�tcWU�h�E���U]�9�G��N�){"�h�$�+h����I2�����(�<%���c����m8���:��$�C4?csZ�w�w���l�m���X»��O��g�
�ȑ�����ӗ� ǧ=WΟq�w9~ދA�mR��n�`�~K$�G|�{A�����I�
��xe��$����D�;�)<�vЅ#87�O1���K5Q��
~ސ!�9;`��s��r�� ��r�"��>��\/� n�h��Ǝ��
��À�|�7��§zro@�����!��nA�N>�ZP���2�F�q;J#�p��/��Z��ju�@��V�Mv�]����noX��0#Q˙N�W�u/ ���V�me)-`&�E�WzI��\�d��7���ð�V�^[�y�Y�Y5fޫXs�qNS�ä�ܥ������7�z* ��JBw� �3��D5��f��|���h��`;�;IJ���:
@�`���8�hh��ȱt:�)21�#���Sw��e,?��;��,���$������    �ܩK�N��O0�W��Ҵ�F���͏��n\�P�^`��ɰ蚀y6/�n;���h�w]���!J*XH��*��^ ����$K2	���� �; ��N#`e��Z��F�^��Z�hYo���Ɵ�{�o������s�?0�2�[$#��)m�ɻ��r��9��s��q�%�+_ w&�i&W7�|@��e3$IS*�2n�!rL�C��k˧ǟ�O��B�˴[��܉I�����i\jj�H�e@�C����Mx��Nw��5"���b�j0�m�(���G��l3��ɃL]#݊�#���٢���v�E%a��;ٖ�3�6�|�
���Y���\N>�W�=�GŠkSs�7��I�aØ	�0�\n�-�t �2�kU�3��3�J��v �b���r����?3��8�q�48K-�p������J���ѹf��!�W�4�-���bk�f)r��Ĕ��׻9<_x�bK`o�8���*zT�6�f��4Ec���\����×����Ј�=�ZؐL���oi¶�	�]�	��ƕD@��,)�~����+LAS�pG���I����i�	{���y�Л$��G�?G�)�%�$Ӳ��ÝX�d���7��-�|%m�9�@�>"�Ή]cV����XѦ�����l�+� �MT��|��ASB�6�h��s_�s��Q��;�Hj�G���BiDf/( Uؼ�Oַg�M�*�yr��א?�S��;>-K��G��]<�;����d�:�ۤ7���CK�=����E�-��\'<�=��j�W�����U,ߴ涵�Y�$i���+̭��Q
���Z�
��~¥XM��ɇ�=V�4�X,�d�G2���"����,����g<z�OۀR�iQ�`���%�*,�Hr�-Z����_Of��G[�ls��1�����m��߳ڢ6�>�Z��M����T�⹅�ѕ�ր�����!wM\;��ΊD�LV���
J�eA�\录h{���҂�O���z,�LG��v��7|�皳��3�lM�l(�Ir���:�s���h�[*�r8Q+�M�#ҟE�H?*���s� g���wEs�=�A5�+be�dz��f�5s��UUҰ���fR5��gB�II���c��Z�&�UAμl�"�Y�9򦥍�}��<�=���u6ts��kւ���+���\�N߅~o�j9��@Z�Mbe���e�rmT�+k�ƌDK�G��\Y�� N�yﰾp�����+w���Ph�I���&QPKd���ܪ%��#�RL��XqL$�i��|�gI�Ǘ�~��9�xBL;J�]'5ӗ�s]K9޺�ȩ�.�U�:<}gh/�xַ=�h�mȒ��v��1��T��Z�7N�$�+��~kToc
�e_�CV>z�ln�/s�JC"�$�hd��0��ϊz�i
g��9Bl_�#]�$ϱmH5v�F�4*E���Zo r�7֊�Y�W�s�Hig�
���jn�
j��$�^����(e�˵�"�q������6�ZVMI�K����$�S-��������[5.��r� �C��$����8|�X;��P��ܔtq�Xr�-5�3
�(6@�|�R^�����6U�g��ę�1q"f��f3� %�b8����䰲p����CA��t���%�U���QD�</��d�1K�X�~�_�G4��=�q4/'cboj��C~���	�Ri�W�݃�5I��ّXO��|��l)Z�j�#����
��y����w��F���Z])|�5.i8�D�Tzq�lk���45�^uzdl�������֤�?�b��/����~�/}.���ᦈBER�`�VF��c�����}����Ц�K�5?��M���b@��>c�˱��t��v�|�����(�zM��D�}�V�*d����*�L�?���w��LZ�Ϸ�e-�f����~;���hEY�}^��Ku�W�ط����-�D��"⫆�C�#��Ko�l�P�y�͓5x�	�T�#Q=Z��wVV޼�˹�ԝ�8?�'���ǝ���0D!����;*����I�Kxw'.'��H�#.��|gNȒ�5o�lP�k�D�`��A=�����m��I�@��;b����I���Oq6�8�2����o����I���1��.S+#�r��)|�(�No���"Պ2{�uL��~���3�S�I�/����6Pw����ēQ]r:�z�h@����P*� �$ۈ�;��ٴ˃&���;�m!��Ad0�Y]��ג��q�p�	^s�ER��&2�3�@u��>9Yaj�@ģV#0�8C�aubGC�G�Y씐^�&k�&>�7�|��9�n#q�����^="Ś�n�+4K�nտ'-���g���#�� �_a�N<�"I���0Ʊ+�҄�j��!�Qv��|��8�ۑ�@�M,<��ŮWvEIo�Yp�ǴXXGo���f:ΰp�}0RD��' "�I{�BZ{�A�
R#.<�%x���3ǲ��+����1im�@뒛$	�1��҆8�ߒ՝K�y�NȚ� �	����e�">�<�F���[#d��|����h��pm�Q�y>�4x-��}�5�P��x8+��&�[���J`A��z=ըu�_���}�J|�=�]þ[����7�@R^� �^���n�_6� �����y>��^s�M����Cb��̙�����r�c�c�\�9��	�
V��N��K�Xs�.�pN���3�e/9�� ���zS;<ab������<`�p�Qw�7��(s!��/�2Ӹ0f,�5"�fݏS���oɭ��c�/�k �x�m%m���&LUQ�B�o��V/so5HNB��@���H1/U������T�b: g9C�:�kd��y�b���&�kp+U5PX>�4h��M|��~�*�[�?h�޷�;����+��>�4��fj��3�+F��c������'^�K')��� +�\۰�:\Na���g� ���k7i����HB,� r��Γ�	�]����C��6ٮ7�T߽�d��ky�Ԁ�G*]�bo�z��#Ɔ���;�O���Ǩs)��w&W顿r袐��V�@�^O��<m�#ɠ$����
�o4����|��O���נ��aP9�5X;_սA�rm�ZP�8衹�+X�� )ks-�}S�i��O�Tw��.%�tF�w>��SY����ԙ!�]%��!=�]j:@�_���0푭�ξ-�����oY?���T��%�0�b�(v�@�僚ҥjj�:�W Y��v/��UN3���}7�<k1UEX�k����b�"�?�ǒ�����CjV��*1~e�X�y��Tc��t�b�qdN�ΰ����fm�9.c4v�_�kI��vg�u0X=�+~"��Ww�W�w�8���f#�0���0,ؠ��
gȾn���}�S￡�r�	��V������iG��?���7��EX��^A����l4�@�(�d��E7�8/O�xk2	3�@���94m���V-����O�I7����G���$(gHVުuD�������p��h�⬐��u�����8��=��_(,siL�+���u�h�AK�ݝ"{�G�
��ۛ�����S-�c5��w%��(r����UhT����W�O�-I\ ��\�e� Lu�� T��[��l�p�&T����A�T{���:�:��q����w1��:+r=�9�h�I�{i�rq\���QvZ�n�S���MP�kh���ņj$�0�k\p�ǈ��}.�����;�6�m�̫��%(��.��4�i�?����_t�Sֈ���y��7�G;��'�7��}��H�xM�l|�z��ǜ��B̿�l	K��#���:?�v�̅�Iz���3�p����{�z�?��*�L��1�K����j���ޗ����}���b�ȝdi�5�\����{/'�&��c-���mU;z��#t5\�8!ٟe���GW���I��Ҳ޿Y����~`��ĩ�/�±�U3L�V���(;���c�ҶZ9���|�ez��^��:C��"2}�뱜�m�m2�mt��|Л y�l    �т5�#�S�\����f7U�7q�G;��0�߹,ȯ�������������u˃�چ�vx�@���[kxw&��/�ÝU���Z��SV46�)1���zpl�������r��:~i���ICu��G�z�!���G`s�o��̯G焆�-T��������X�oՙ9�g�����2r�e}m�.�W��^r���Z��~rM���V��!x�hio��5��B�����)�5����:�������;I/�߭`�b�D�:�t�G9Q���^�Χ�	�GP`;��"�
��Su؁{U? �R޲�n�W����گ���O��fA�`i�5>G�t����<�v*���m3)#ꒋ`M�TT��s@(�-nM��1�����
�a����.�hQ�3v��,��ո���E��3��(���ss���}���y�*�J��h�;z܀74R#�o�C�IH����r��oDu9�+�>Q�D��sV��V��7� g�.�� �5�G��k�JV�����5�Dn��r�O�R([�����
��|�����%%{K��@��y�4d��kM�D��@�^ ����C�Y�a����>����;.���h�#
��{0rz�ݮ0dM��G���"���v4*r# ����w���fF!R�� +N��m{������e2��ʪ�
�H��?�����޹�?�sB&e�����������4�+���}ֳn���۟0�μ��D9�gK�-
��#�84�]Տ��/hJ+ow{$��;7�tǵO#����6�;|�nQ8wx1�� �k�C�>���㇧̅+ķ�W�B�&�[ۓw�5 3�&5��t$�D�^z��2V͜��9&`�,,�;(n��fN�Q�_*�y����-Iw܎��=��V�P�%@���%Q)���I�s��dg�� �*b�F7ϊ����J`:DI	�s�[v]v�N�o�Ո�����W�P��^1>.�8'DN�MҕW�yE^�R�� ~���Wu�#P[�VMK�k4�݊�m͟j7�V��r�	���v�̦Ľm>����{�e�0
�x�K
"󣻑�*��_C��5x�lX����M��>���)��rѢ5u��aߝۙ�r�����L��bi��c��q�ۍY��R	o!��l�9��J�X�f���Ր�O@��&��-�1��9/{�YZ�c��_�S_��%��c�+�jH9������Ga��OJ�0W����pD�>�'I�=6��\{į����l��4	���Gف{*��,�'O���O�8g��4��į�N�H��/�S�l���진�|˜ȝ������)5���+<��H�+\��t�Ӑ8�H�����Ӟ4N3|��d�E���Z<�Ht�Ƿ���ŏJ�U��j ���8�!N�Mk���x�ſ�?��~K�zkx��l��z֖��u��I�C�6��W�;kϮ���w)��'�C�T)�٧m���;Ol�6��ܡ�X����T���[�~-u�=�i��'�=X#��Р��Z�����6�j5��g�N�We���x�ܤ��r��ھ�Ys C�y�l�ػ{�ӧ��JT$�t6,�-!+k~E��T܆�Ժ�A\lzR��yV�s�#^*�s���MK"6}M�X'��֮i���I��0���t~�g �z�r5��Cb �]Y��+��4�51Vڜ���eb���2���ٍ���B�*K�Fzp�,O��!�H����G<�5H*�[H���7|�5���;Ӥ]kV��0�-S�{�̫l�5����ԆYxum�:Ԧ�(��� ���`�d��L,�ԓ,��-����p�	kx>+� �Nlrp���gF]U���W�C�; MI9X����:��O�i�S��Z�W�:28���O�&ug�Z�
��"}��j�k,1��x�k� 92#��Ȁ�����W�eC�EC�\��y�E Exi���i�>r@(xn�=nTCN�]�'�T�����%�O��8��`Z���Z p��~����H��ʵ�E
�t(��I��o�Az�rou�PKN�Ԉ�yo����ƭP��v��s�Bud�N��dǤz���ӛ�ѭ%�I�Sj�]�A��z�O��ἐ��x�%�i�-�_o�A���]�ZEC�V悓�UA6[��8�1�~BuS���]�Ns�~\�52�2{����	a�����<e���K�i�6=������e�C��*�]�q�dzJsPRq"�;o�Jn+���p�O .��}�e��s���/�zr�AZ��o؊�z����8�y�'+O+_}I�j}]=y�Dي��no3��i4ɉ���Ʃ/��sx[��Y(\�r^��"�v��Ql�,�Hc���þP������-8ⳜU-/�t��d�/����	�������ɞ��qh:L<b��W��z�"��[���)�8�A]��,�Q��Z_[A���՞�@.H���1���Vw��5ѱ��r��i�4���Θp�k5��#*�� 1� ��뎝O�9����C�M�YEMj�wqb�?ѡ��n%�{[���7��$�¾�Q�D��ȼnp��af���i��ϸk�Wf�Pfo��Z�^�w�L!��7�ܠQ��(�3�z�.�>U�tt �u�K��/��>.�Ӷ+w�2�&t�N�{I��N�ܐK;��4�DTֈ'�=��G�������f5�+p]Q��ʔ�]�g���k޾�fve!�ڷF�w��J]��$n��hCJ��+�m2zL%�N�"=���8���8��&�C�t�q���i��H�}'S_	��|P��ZSގY=Inc���>��X�L�PP���vC�$ß���i�w�*�-Hr�w�]��ԓ �Ǉ��٥ӵ�ti�?[�~$h��qj�<�J]hKs4`-Ԯc����#*fE������P�钜�ϔ�W�VF��!q��{9�����2�����{l����hx�U�-!^g�Ք�w�џ�ܛ��[��l&u�����U�L��J,a�EK�#����_A���(�Lj��=�̒�\CY�T�1֊9*0e��le��+�<�Y%�]�:F�k�Ȫ��4��Z�tV�Q��X�Ds��d�˭�������)�e5K"-�-�`�'���;��5������ψM�/f:E�XkP38��I���{.Ң�������0`��yB���c�`ُF5�lq��sj���d�}���-���3�N�9Mw��<?%S���g��	�X[���@�+@���c�b�4X~[�����&�N�p����v�?r�ni�B=�?l��^$gzpXַ����b��f���db����!�:Ok�+�vcK�#���|�����3ē��g 2[��8��`��Q�4X�M�$9�|4��ގ�����]X�/`�eB��#I%͵�5ƹ�#L�W���g<"bn�2�بF`(`7w��u�2�i^��ׯ�MU'Qa�a˛$��xu�|�7_E���Y�[C��Gw�_[%b��9��p�X�G�>P"0��K%T-�¿!�f�0ɖv�A�� ��i�4�1>��0~!$�ʞ
PKO�DN��3@Z�b��B��\�.)����n(l��*���B���mz(3��@B͉�'�l.�g��w�Le��~T	$N��ϻe؍����.o�#��&r!b�	o�w�¯"�l��a�M���%��teܮ>f���n�9q6H
��\m�3I;*���<,ć�
j��i�
�̐e���+���tZ��|8�������f�lU%A��U�8! �"�1��Ӧ�*4�8��ZV^ShQw
��� )((K��i@���Ê�u�Ji�!�gYy#9�~6��w���\�Y���ri(Q(�g��6љť��s�x�R�;ՠg��F�%��r��\�g=��:�I@o�{Xϝ�7Q����Wk!�������h����q� <%�Vm�)s�;{ON�%�[��H��o�c�֐{��jQG�"�<�)ebc��oU.�Qj0�������%tz��,�kJd�wɢ��H��!�    $�h�4Y�D⃤��x�jt������f`=���5��'Ā.SD��8ʭO܆�9sЊ!6�������`>#������|t�[]q	��A�Te6�h�:�&�(�F��m��`x��0��EfyB�пsR�`��mTO�R�;Ն�ys�=�[�5�ǆ��7>��	{�����g@IѡvV���$z8�-�X~bU'.��0�C,����"�,���>%1G���;���D���d�b�t�� D9E���B���p��FZ�3���)-r6r���>"�ܢV5�ڭ�a���|e�<�R ��*X(�t��i��?@\%�rdܬ��Y�A��L�Y*gh���"�ר���ָ�.錏��n���Ga��K���nph�h'�Ć�-�D�%�=�9�vM�GB��:ވm}�n_}�Y��^\k���j�lUV��$o-g`��E$�YVV3Be5�ʓ%�l �C�ћ�&sYS�l �U���	��sWy �W���w2��z��kG��B Y3� BJ͐����a�4���1+o�����B���=�`ޱQY2�V��Z, +���3^��ntpr��Z�"��A��cكk�|n�9�_OfM�O���Ş�G~òj���y��ݩR碹�䶁Q��͌�'�phӌx�(�Ga��Yv�0Δq]r����d/^K���0�
�R�Hg��vw)r��� S�ǘ`�vP��v���e�{�n�dˊÑD>��ȥ��3��kj%c5�����I&\���5������ic�`ڌ��9Me�E�w=�%�uh�I��lT��Oc����ȿ^Kav�%3��j����gߓ�u�d��Q9����ƹ��n�H5-����漢}���Hم`<RU���@��� q�9�̻���=���[��c���-��znំ!��'�I�1 �o���xd0�NL΁�C�;�l6��K0�y���?Ϯ'�!�w$s��^�P�SW��g�h��f� �\�E#	Mu/#a�h�ǲ�l�.�W�Y��PL���}�B�X�X�ߠ����`^4����6~�"{�$�0�p�y���R�yƙጼ��E�f�:��8��Z��%�+K��%����@q9P	�:��-��/��A��Ǡ�_%�9,�bIv^I��y�,*�=սd9}�5�������*:�D�o�,��W�-�NX9pг�kD(�Ca�@sd/:��湶�yDa�5�VX�� }�O�D���:��������tV�rّ]%�HAo�M[
�C��'�)6Y��V��+��:s`d���PR<=JM��j8<�'%�����`��_r�i�X��P��]�V7�ܺ�Tl���_[L�A��nol��yW�=x
�D����͂0X����מ�`����wM�zCs�$�8��/��8:��w���7���_�y����wA�'�eH~��q����0g���Z�,nK��8�|m���d��R��5��~��C�ԛĞ��^����!�q�7� �\k1m��V��L�R�#1m���#!F͍\�goDi&2B��s-s6E3��T��q%5��K2a1@�\M�`>�3W46�eڌgzI��lf���ݮ�[@����X�V(��������ѩf��I8�n%��+��%�C��{�L��| ��{�%�@�P�����_��ݶ��`�s�G�m��C��1�F3��F���9��r9c2F�z�<���j6Pu2co�=t)I�H�$����9�&�s){]ǘʬ�Wg�/��U���c�����h�Њ�Ӛ��[��ڑ駗���.��f-�[̠��_�z�=u5���81I��	U>������Jh�m��b�&��c�M�@8�Y�0T����_\�{���L���S*�s�W����1�2�7�'j� ۛ��{9"%�9z��9.�z��A�DFӝ���y:\�Ҽ�,};呈�U��e��-����ck�T;5�O����:��5�k��4���m�oG�_���9�vG���W�p@�1�dbs��9P��%�{���j%�5��q�� ��2Q�f����eһ(s��Z�>�[�&��§rH�w)�|d`A��ki��C�Hpx{(�*&�wEis�bC�P]%����W���n�-G��q�	��/vz5[ܘ+�N�i�8~���i1c�:�����'{��=fV���,`��7��)
�$��7��r�U��k	�^:X2GT��O�﹆�Ļi���-#�%7޵葎ݶ`镋i�ޤ��ZV�qZ<�NȊ��NpńƛW$�j^z7���=�4�ѻ��l].P�]�$��8s
��}��X�o�ݡ��7b��7�hX#s����vXavUL�?$��;O���!_<pkj��3)V�<�F���(�~�[�^�-:�`�,x�U%Ly���4Oӏ�w*������a�d�ش�q~Y!�Qx�C]E���Gk�݆0f��w(�^���4�� '�	�7e�\�|4ʙlv�%���n^oCMݮ����,���9��2]CM)B�`ı%Eɚ%&d�W��5l�8�������Ef��O���ݞq8]�rS�!x{^�x��pD/q��k�9��L�@�F���ޭ*7����Z��ރ040��ci���܆Y�������{������U ��8|��Wԍ�!�'�2�c���͙{��c��a�\���%�M���Uе��j:��fDퟵ�=� ��C�g�`�?c�JDF��*8x'���G"�2�����?�.�j�Oӥs ���?'3�
nI�w���-�2���]�!���݌_O�lY���1�]��;�'I�o��j
��2T���m��j\��A�\;��uAtF�������=�?�=����ѧ�n���9dY�'�Zol�����h��<�j cSϳ��˱�x�f�y*<���~}�yߓ�)����2����n��/c���|����$��)�`�2z���7�����a�0"�1���5��|d�"�IF�a���l$��|y$Ƿ�w�rK��?���E��|�-�M:v���܉^��8&j�m,&)�!�mw��g-w��1΀-76b��䂘Zp�7m�GwC�����*O[���[sZ��ϼ�������@-��%���Zk١,���e��p�$��?��9���h�|��5Ҭ�e-w⽸�n\�& �Qպ,n�y?F��]{;26�HP���x��k�.�_�5��Z}n�?Gq�����5a�#�-��DZЎ��_ ��}���n�輪�� ���z�^�!~%.�������>��.������&61�fsh�ܮU��*C�!O�����1�ͻIC�����"���@ \E��T�䞏'��w�A��1��`�H���=�M���|����A�c��<����j#�
�����4Q�++_	����%��4�==�C�c��zO5�@t�c�Ô8h5�X�@����$���Z�*�_ɍO#�ߧ�����_}��苨��%�{w>����C�S��4�rN[���Tњ����}�2Y�"oug�c��o]h�s�3<�O�Z��/��4z��1� Ua/k�à��;��Q�\յ�����b�����?�����z)�I���,�6���7����9�}s���BM����C��S�����a"˩�9a�M�ٶ�.�Y���xe�RG:7��p����.�fd��r�}���k ��A�Ċy��i�32���X��[�AUq�oP���'�n'etw�6D�
�u��i��{�{w1���l��Ո��A�U�ܯՈ�RKW��A�I�5$#75 ��kh!�.��=/��@^�F�kֵ�)t�:�S;/*B������&�W\�L��ظmU���\�I�H��<?P%4�m�+� Y#M�z��N�4��j|�)���ǐ����CwI	�hG��,֑V/b_�����{�qy���ͯK]:<�������y��T��]?8Ƞ�Ce��-��q�'�����m0����i�n��/��^�+�{���vZqU��%����I���E�>q��&�-U@m�d�D��L%����vtϋ��A��    (��ԥ��z�,��HBjO�P�Z��>��է�L�������B2��%|u�K���{�@9	�&D�Y��ۭ����6���,^���i��~��F]��*`�rAIY�N�����~�hrl{�B��`�O|Zqޓ?�?)��a�zM$~��Wu'лҕi��t����gM�&�g�?�*��qɲ" _~?��F:���m��Klb�\�OҞk�m�чSۊ��OM>H�ؤ��?7�9�mBԧ}�Nk�-��p�Z��1�~K=����O�_ՠ�Nr����i`m��VOd�/z�x+�!]�E��)|�����F���U�_9��Q����!�H� ����F���n�q#v��N��e�^v����c��bn#�h�d�%u�W&���*XƁO�w�.t�"��^��/�8�����>vqё���vׇP�Sm���Ѷ�]Zo^��%�P�n���A;$�a���4-��UX�����:g�C�������
�@�V�c��#��F��j!|ޱ�	���)0����1%����f��Q{�@X/����D�T�z�⭧l#�i���36�¦�x:��1ʠr���w�l8|� lc)��Pq^�ݍj�4$D8�Ǆ���dz��CP�`�n${D��Ù�&	�c�d��Vk�[GnR�2��5�a�q�ᒜ���yp۔`;������u/���X)|JÝ����K�M��Zq[�(�B��E�.ܦp��ΒuM�)����V�^�l./F�8�-�)�TҒ��y��|6�hʽL]LޓB�b0w�D���^���nA{ZEKn?7UȮNb��}(�P	YC��~u��ΊH��_�Y���]2��:~�{���z&��v�WG�S�0���h4Q��A�~��"�,�04��~���w�8�9��G��7[���U�J�,b?�$#.�j~i}���띸�2���!6�E���ӓe˵",'֏[q��ą+�sE�)w�F��~��,ۮ� �1�2!�z������|�f�>�z
f��[�*��?�<�mŞv&�$[���=�>�L�XܡSn]t>@��k+\`@��6e��`R0D�L)�����WaDɾߛ\�n�Y�7ƪ26��F�hZ��۴�[0���-n�y�<�(��"�D]��V�k�Q�����O�-ϞO�p��ll���S�	=p�W4#5~��pۯ�"!�8�#����y��Y�a$�������0w`]����hɽ|�B�X��Xjw���=��7��x�V��>�;��z���/�(����v���P� T��|7��f ���^���w�D�r��LC?�����i���b޾z��j���֒B��R8��w��{'�yy�S�2#˸��v�K��9n%���{�����~��_]���#U̴v�GaWϣ�mٱ�u���6�^+���͡�F�e��2?N��Jhkp67'�"q�����i�J@&��-z}#��E�����O`w/<�s������pᷘϺ�;��(e��"���n'��t!Ae@> »����0�3�s��ہ�1�ءc�I���R�l�O_�7p���</6V��8[�SOc��d��I#M�J��|*j#�夔���y����Wд�O���ё�["��6&�/���#XD�&w��8��l�鐰�}v�{9~���[f��#X��6k�~�C$6�-q�韪�i�qi��b�=�<l����!�<1�E�흌�v�+üi�y|���G�R�L�ڢ�(�Rf�����3���u��=nQĳ�̇�wܩ��]��4vo N�q��2���}�Q��j��'�
����&Ōy��9EuQ�S��R7 k�R��y�u�.{Y��+Y�>�<���4�:�#��/���Avm�q۫pwB�er��&RZZ!��2���^�N���?��ݧ��ص9�N���|\��ńH~� �@�!��!�`A�7q.y�_�N>oE��Kx��x��[p���Odk��xe���vۓ�m7�Qh�M!t��n	\�?[���a��Hs0���nP�a�sR�����������A���s�`EK�b:�\��
~�v�����*f��B?�i�������$��&�iy�D��Y�V����x3{|L�-}B��4;|V���9s}7U?�s���;��o:j��|�O�H
4�dmE��ѝ��V���#����Y����[E�ء�9w��y^��,F�r����j%���?|ugD,B�0�&e�'4\�͡�6�
s5x�O��d�����x'��[xB`��[S���)�D�	1�Q��gቨs�'���ꦥ8RTG�d�c����s1�]�4G��'�)�����P��F�2�
��	�I��D3�vS�������h�bG�����.���E|�������ؤHϭE2 V{�����f��A4�w8Hޕ� =C����ї���ɢA�m�|�.��fH1,7���Ci_��WQ�ځ���Z	�I"�ZEX�*�������4�q,��!K~�|ft
c�:�e·'���O	*�A�u�ZuB����d�����J�dHwai�o�l�w�q����OY-��4��??���~�	41�@�er���l��~��M����|NjT��\Ʒ�d-){
{ ����.V�<9Tc�`�vۇ7!Q�M�-��������j���ɤ!cR���&v�2�n[�	�Т��ӥZ�d1�2H�lPE=od?��5ceFFX=2,��o_$�����ĩ���*�&�8�~Q�Aou�@��S�a�FN�z���ϊ"���:<�q�i��w](�[���q%y�lr7T1�WAhȥ��Xma��.�[�N��-ƺɋ���f���s���6+?u�U�>rB|�mq�yǎ�'���$=���CI�
���&����$Ěl�yuYߨa?�&��/�_���a���ޱ�l�t������%��Z�tֹ�����q�.���[�f��n�k�����d��$s.[��]P�h�#攝/o{`'��?s>ȕx^2)�.?i�n�KP�]|�5�s/�Ԛ�
�l� �pL�C�͠'�5�a��+k����_��������R���es��AK�2���*���R���"w)�������`�o���`���j1���{XJ��� ��c'�6\�8�k4>��q4<R��7pD6O�v�3sQoM^\�DHr�4����P�,k(��/�=I���
܇�DY�9~
t��Z3��U�M%x�$bl�n�K2�d�d��U�2�Ȫ:�+�YrS��Lͭ�Ɂi -Ғ~���޳�I��{,�@7o	����*gl��ƅ��հIE+���w�Tk��H�������U��|mn9? ��'�p�����n]dI����'�NW��*xk�nf��8%�o݅����ʽ���*� *���HЉFW�Ya���)���O��`�����-�۩����Y8g�L ��h`���)�����s=VoJt���)#�����:xc'B�҄��q���4�FH��S��E^��R%��#������Pⴶꮣ�,����[��V>�;s�ď&�]�$��� �K �4����ߓ���ִ��b~��.�\����Z��|�񝞝���-��/(i+,��U��3��'������G�I	�}$�͇��[���&�����+�HH}K<�p}��W_�:�C-7,���ë�4H}Z5��]��{X�{�?�7��[��m�qA���'����k�d��,���&Rq�=��^�ʚB_8�ߞT�U��3��;m�Ί3;Io>��e%�����W��c��l�/��<�m���(�%9��7J��n�R(H���6�PQ�q�|���R��&��	6;����� �k�F�s��'��5UJeX�VfWhz�/>r���PO���ڶ�[a�#Q���c��m.Dr)����a鈨���$������w=�Q��$��H: +��u�X��K�!}Y�K�g^��mQ6tY̨;5��K킟�-��&!��B�G    &?Mĥ�d}���[&��"�غAf�}�!,�xN�5?�U=�(��N{�`��^D�2E�ՈYK.0=���t�
z,p���X�5�	^�d3����Z�a�$1���x]'�\!e�d��_ju{�D� �����=`|�'+�E/N�&�	:��d�+���V��䭃P����%`�D��į K��n�|@v�W��HbJ�z�!��"��uc7nXL� �`�U� �p�ZN�a�f�I�����CI�/�Kc+�>n�00�v"�{4���;7�,�3�'����zL� �������QlE�,?�%X��=d��AL��+V�e�e�� ��K�\�p����9���>��!L�#w~� �*�(
�~jr��M�*WO���E��������}�5@_YE�w&��<ń?m~�\�it��Itn��OZ�G�'U\� ��3>!ߨ����6��%�WԄ����,�J�g�����k��2�AʹG0��9v2�;w���[uO��}G֭qFl�i������>�mxwtM#����?:���\��/�a�P�/{��$�����r؏3<a<���a�����'t='ZY0�D�C�F����_є�����.��@���	yd+��1>�#�b&9��fVqH���j�/�y�,i>�*�"a����>���p۩�z�FJ>"�����^�)�ߦ�?��U�@�J�
�5D��?��v�|���j��N�0���L����*[!Mj���Zȋ���-Ø��-���C�sU�RK� ��=�r?�[��[�-'�����H����采��Sk�����������F���TBHt)���G�"�&�;�Z�3��a="�.�X��[�HU��k~F�_�!��p�p��X�'z�f���˻��È�&�0�p"բf�E�H�A���i�^��Mk���G1Էp-M�hD$��q'P��^kQm'��T���PIDXx�݀l�����S��n�^�V�gP�m8���!f0����X�B.�Hc9K$���<�7D��\�Y��GT(��jݠH2;���'��8�I%����/�x.����$���Pݪ.�A�t�������L35�7ͅq���"nz��G���o�]�/�%���m�IC;~�-���vp	�v��EA�W����ŊW���|����8�ņ�#¼ �A/��tO�Z��5�K#SJ��_�(��%'I�����v7葃ςy̓A�(�F`X�(�$
bކ���7���pW �����\O�8	�)l���_���˚��\wy}�lq��i�����A[���⸪��`�ĩ����`��q��7�8Ni�f��Nba����(�Э&��ǭ1M�kr7��O٭P��IS@)e
R�~�R���U�T�3�c���7��{���q���"�/`�s�:.9���ݙ�M<����݉"�I��\�,t�����h������/ߡ�P3;%52d)Hi��LW
��9���u�([˫8���cx��������v=~]io��Փs� ����R5�)�+L���H��q���D�Z	SC���k� @d�}`�.�vW$&�sSHܻ�9�{4�C�X3�}�c��vZe�ӉI��C��N�I�,�(�υtcVG&���.�'@W�t��I���xD�Ryp�S#R^(���8��ĻY�좴'\�kqbW]��lY!,�U�P�,�Fq8r]\	o]#��*����� z��F��e}�h3s&���dP#1�R�F�snIHi�h�W2t=O>n˻����11��y<��,�T����6����J���g˾���}�A�¿�&{z�n��h�8�עo�&S��$N�cXMd���n݀��Q�5y��>���%��W�>?�Gtb~��{�U���@�%A��WD��Î���i�RU6I����))M�U���N��^��lJ����$��*��/����T���p�1�H�XP(�	�c% DjAKm[|��
�+qC `�w؃Qڹ2.�\�/�s\L��������� �y��o_S��4��h-F��͇���W`G1[��[!���)/����п�e�C��-��o�z��w�@sT�д7�l�mV��Σ����*HT�뚴&�$�Q�Hi)aF��G�:������̅H��+"D"��p��r����`�r�f $��`?�p�$��4�?���.�M���KB��qzH�'�l�A������AP�V�����+�{gҼ�+�ξ+��Q�� EF� ��n��4��y�����ؚ �b|2�F��:}���~�t�&�8^��	 � 5����5� �m��6mq>os�'Z*R<�x����~�/��D����J\��"��skR��[�W��2$\m�"����(r�� yw>����$��n�7�>�NXXd�t��9(5��ū�΀5c��9��e;�ȑ���B&�U��B?y�y|t��Ag��=̵���fd��;�#�s�ny�&,���X�'o���gf3[F.�}�9���mH��kb����EE]�J
x��q�6eQ^}'\=�(r���6�������(p�N����سF1;&��-�n~Z4��vV����j�ڕu��0`�n ��BbgQ��{�d�q�K�����SvDw������D�.>;w����@JX �U�8B���  �`���F�4�q�&�٣D-lQ��4�+��)a`�*���\N�+G������̣Z�ö�5H�,�hސY�	齰���(~�b���)8��^�Ab��}�{%�6R���R6ؾ$a{)V v�wK{�Lj��f���-.�K
;�U�@��hFxL��@h沍����3�>n�3U+˫Apf���\�(�4!�V^�yy��~��X�i)�~�Z�/f��*��K�4?ʵ��o�E6��-i�n��'CHD4��DT�Ӈ��e#���~N�j�u��i�D7~j��#�7u�7����n̍*nA{�X@~�<�w�*���9�Ң�2�����p�gڱ���Pp����nj���@f[�n�X��GT)GN����O([i���4�R�+�+5�W���u��T�P�P"�-X��ɂ��>���7��K*ښ8��Z�����2K��`�6���r��X���aw�Bל�9rYF���O���`����y�	J�l�
�JN�Ex��@*� �'��L?�	:�WPHmh��'\[�2
��]B��Am��*�j�8����L�-�B�`N�
������������s��`H��|$�a�l�P���W���%s7wE?]P�52��F�
��� P%�bc>|3��59
&UJg:�����s��\�z�RD	~bE�e�a-��B���27�rlQ|��a�1ںן�B��q�'��y���)��iBZ)�i:Oy��Q8zhZ��;�Q9)��*��k­Ź�W�B�ҀG�!���c*���$^�{�^p/�V�C��@�%'a2Ϧ�i��F1�5��[�X}n�&����M�}��T�|�p<����̥�n�!�
�]��ֻ{]E3��ݾA+֑Ț��w��9�л��R%��	�W�Ï�Sĭ�K����&���Z5�O���"I�9nn
�+^R�K���z�}3�6���%e���[�T�h�`&O\�r"k<�A�]��b=�C<��_C�e�NӮc�<�8Ś\�(U��7q��|�������9|W�l���ˆ�Y��~ZǓy� � K፟��ݨALl.��
[����؛��h�{r��=_�q߅�;�OA�� �k�\~�Lf��8��������:�2FȒ��l��D@��naN������t����c��4 ��,R�s2@�(�^"Jv���aJ@s�D�B8j�L�fʾ8��\��
V�x��q*��!��kG�ȑޥ#.����AI����aY���,2�Y��{���4��n���v�)�>ؔ��J��8�,
R4���5���.�:KCk�`��V��9�	y�������x�$�Vl�)m�؃����Y��1w��ݙ�I    	��t�g���Δ���H`��ݖ����f bj*Z��'���j�N��zfJ�Pf.ո4c��(Q�����R�.�^���pfJ@��:���E<d�'F-`Խ��@+�U�JԱ�~e�O��#�v�p�����*�'$>�D���sA��y@aO�Ra�	8������ϴ�NP
{�O!q�<C���=:rVmNp��tt�WPܵ^P�`9���2��3U�le	l�F+�ܞ�t
v��v�ȸD��A%N�8��gUf#W�}�Ƿc()5�y-��b�6�e�#���4���9s��/��c�l�2�&���&ebڦT� ���!�W��3£h��1Ӽ��,k$�h��@�2�6pb��y&� l��v�3��Cy ��hc�k���}(9��֨~%�)�D�!������ �W[KxZ�+�5���G�);��w[|>
͔ÅH�/�A4Y	���!�����V
7w�V��#:@Z��$�ǯ�,x���)� �w/�R0�̺E���۞����y^��7^?�mkr���6�R�$R�pZ��o���j�}�J9	�EN�v���5���t��t�/��X;�k�$�|%t�l����܆���cO����)=E׊q�/l�⇂�a�F��s@�)(os.n0���7�@oU���ZQ�q�߸��?�%_���b�GL'2��Y�dGv�ſM�?����7��`HR���ƽcZ��� ���~��S��5�V��U>"n�L+j��̊��~�A�c�Ҭ��3����T��׫�3.BLv^�`
�[�h��&�pX������N���S�N��I����Z&>�w�r�,�g��gQ�����b2��� a\{*Px��.3'����W:&D9�6Y�<��A�ҿ�����m.J��wĈ)�K
kAZ;��~ׂv��!	m�O�NcUhW%y%�Bt�$0�'fo���%״L���I��h�ŜA���}	�Ǜ��?
�CՁ<������/���]c�[��Hx�;vV}�!�/���J��A�h�(�k�+d^�V��֛z�ˤu�ަ���Ï�U�1N}D�E�26�AVJ��#�3�6��$��1��Sh�N�aQ؉V�6@@�r�x���h��ʲ0A���Mx��͔�����*�ǄZ�Ň�z��h_Pq\Y�(I�[%q��V+
$���s%mܫ�}�W9��ךZIL��N$�3~Q�v>Nj5��$g��C*�Ž��/8��ۤ�k/�_�w�`133��\�w��2��Yv�1����|�,6}�^/1r�Z�|�!>آ�Ӫ�v&c��Θo�����������E��"�֌�V�C�c���MZr�;k�EY�A��n���3$�y��ே�ο����?
΀���-g��|�w���~�m�{>��<`��u������![�~��u?&R�~�F�C�7��q���<�i�-��k�6���Bp�0����.c8��}N�m'����]���?�xe�0G�Y��Ega`Eb��ِ�<��l��`Ч��E�N\�}ƕj��^IG��h/�J�+��fdܰ��%�|��Ўq�G�ѷR�p�(�3/ֆ�x>>#�>�h��h#bH�NwCD�|~i,�N�+����[@��w�f>>I����,>F.i:F{=����3JF���씷ց���P>8��g��x�@�(���
�Yp;`]"�}c���l �"��S����S��)뾹�`TY"�\�Ym��>�R.$�n�O�+������O8�"2Л輭;��\�e����6c��/�?J���ޱJx��^��OVpf���|�ȼ<^;�t��n|���/��{��R��C.�ꅴu,Z�dށ������=�Y��wf�O�9�
(j�G�p�:
��m����6���[=�K����J*]�a��6"���ul�"��HN� ǳ��ER̳����
ix:���� �, ���hA��n�P'a-]�:%B��'��؉N���J���/��V��}E�
wb����l3�M&F��鎹:{z�&�_wX��e�1t��;���cb�vJƍƿA�Ѱ���1�re�o�ױr�1�a�"۪P����� 30ȑ��4��?j:��{��%��kRP��.P�7�N.��J�����4V��rcۖ)�}��|`��q�Z�9L�s���9("��c��
3�����R��FU��" E#+hǆ��x�W��ʐ(D�Cѩ�����N�w��M*�{���w�B�^�7��)�����^���	;1b品~{V\�k�d�ϝ�Q��wr�����ӊq>�Ԡ'I�:W�E�r�')�N�Y���������Z��|.�lt�;�s73V4�s��n,$�����#N᰾������x�]�Ϙ��j;�#�G��u����t>��=�	BGI���f���H��O\��о�X�*���%����ss�,��1��a�L�o?ᝨ�O�"�E�c�:IZԫ9��c�Y� (B>O\b7yD��S-+̪���XA޺`o�?����fܒ��QM�*�GgqgS���G� =9�4٫RUp~�=}���*�������?��MĮo�%�(8�&�ľJ(UhO��0�͍KΚ��l��rk
\qc�	7�8u����[�+N/���K���V�����m�{9�<�^�/O0Р^/�J�yEe-�y�*.�a���-��=^���/��t�y�=��W^�2�B�!a\��
�ow$V\X�4~:i��)���}���H�Nc�q$����b�!��S,��(��� ���
� ���G���4��n�(o�G$�r
<��Q!�o����)�jF�����ؠ�˿=��
�*��$����靪B��:�` "���r���8� �M��UĶ�W�j��<b�ռ^\���L^�	����`v7���y��-����;�G�%��&�"4��:�m�o
[���N��PH��}g�����d-�t'vU �i���2��,[>F�W2{}DY��l��_��t2m�M2����kF�ٱ;]I:k0��d��PN�ӑ��7�%����ɱA�e{�Z4��)�z��2��G(98yj�ɤ���$b5k
@)�i���Ë�SaT�7�n{��M	hz�p:����\3 �Ug���)��D�؅H��uL�^%\KcT��O��jp��J+J�׈0��Y��r{xIy���w��6d�����9��7�%gi/�d<��U���V��¤�blA�^`v�u���[(`ߣm��N�pxm��۩�����<G�+�-�N��^�wLd��ba�\xKHAR�@q�{�aQ�P@1�p1�n����5",�G' <6"��q�[�������ޘ������ҴzH ;Ѻ�����8��}eN2	�Bf�<���pj ��R�E���a��%�����֫mUXY#��!�ve*�KC4�l�Ej��v]�E�p����^xS�5d�́�O\]![��U�$�s�u!�W�T 9i�=��s�<v��$0|��9|[�)]���7<UW���ﶱ)q��PT�簳��B���QPs�@�y�j��.R��˫_`�d0�ۑ(� ށ6�Y�Ω�V�E��$�d�.�8E�c�p��2��2��l���P z�.���m,:y���=X�
b��ц}�[�e� X���%�h���H~�gK�,hv��c�u袄���1v����/!)��J�F͢"�(&��kF��:������4��it�L�S ͭ\�Nd����3c893T�,hp��7������:}\��H����f�2� 1ԱW�����1[�>x��8�_��|��gHH������,0�:$���Dv�v���Q8��Bg?�~���D;1�N��Pi�iV�����Y����y��|I��؞�I\���	���j�H�%���G���⒜�9�d2�g���P�
ء[TV"<�P[+N�����E�
�H��z|��o�TM%�����I||�g��a��MT=�|�z�(���O��    ݝ NxYE��tn���:W�,+}�Ҁ]��v���\Xy�-@����8w����'�������a�:��*d=^�V���PH����RJ���w	(�Lߺ#A����'o��(|���S<κ���'�P弻8���m9*;�Ēy�_^e p<�ՠ�89 ���Kҙ�8���*=���Ȏ*�g�Wp��]���8|�ad��VQ��q6{كU-lu�}Wi����x-k�A�^��S�ͭX��EaP�-β&d�q�gp�E�S[+�9���)p����qW�Z�����!U����V	�a�F�,�/���H-Ϋ��˕ː���U��&�1./�\�ҝ��%aw�Fbh_��3[�+�s���f ����"Xvu�\Ep|���0\�@U��iA�B���Mffv?KIu��l�JT��|�)�q	NP  �ߤ�����:�R�pQ�(ũ&�@����+l��d$Q���5K)���7ƭTD�yc���Ő ;1�IY�J;Xz,Բa��rGA�)+D��Q�l�>����h�r-��	$��繇��*f�
�+y\���J�&7��`5�pӁi�r���{H�U��K\��=���2KW]��iY�F�!d���W�Hl�BJ��A�!h�C�dc� fj�[��r�WUh����=u�s�Xl��؉�)Dǀ_ ;J��k�.G��Qvv�d��d'� l�ϣ�M]���Է)����C k������'x��I�m}��ݜ� � �Q=ӿ�b�(��w�$8�B��	v��8s���j�4��{��\㋝�Ds� �C��*���+d��w��[�'��9K�'��B�S�"��o���z
�O5�(�*P�F:%���4�܂��#��C5����|�%e�qr,b���}��w��S�6h.@�Y�dRW���*����
-�R�,�S�'Y��^	o�&�D�-nU������%��*)1��}�W�B8�/%M��Ν��/Q2/N���@����5H)��3\9��#+������4���� �e�ԄǱ�N�b	@��y�U�_��O}@D�߯�{�e�[n��j���T5q��kq��F�N��5x�{��H)��qԧ�o�1v�/#	��d���Q�ޅ���ܲ�@>�>cZ�N�2�i������{�3W�8��9ە����{_J���1ؕ��Yt�1�q
����ח�NZ��`Ov)��?M��o���٬�n#b�)�飜���ʁ�� ���5)�`�I\G4E�(�y��X��w�|��xM���*��%�S��i����O���5M�?�$О$�3L35E�:&M��ӷ�+Y����,�>-'c�GW�K���t2-����y��.��K��$e^��i2�{����&F�I������Ĺ�߉B���>��+eB-�k�Mw�ҵ:�]���l�j}GhO�ǫ��>q�w֘Q��Q�2�B��h�Ps,N�XE��y��c&:�ԨL,�������Ȓ�	>�CX�>�pqr�AKA;ᶑ���ɟ�<��g%8{m�iU�5%��`�ټ�j���,/0��,�E�{��{9Y��c����C���jQ�0+ �a�����-%�«��=��}�K��b7�|�+����wx�*+
o]]X���ދ_l� ���o 6UDj�uEsx[�#
���yW^@q�k�"�J�*�a^T�v\ ���L�g������O�Y��,Ғ�=f��-^�6%v}�1�\}�
�DŔ�H��tr}B	��9�e���8�?$�B^��	�3%�b�r��hq�D��,�F[v���]�B������m᥆��g�*��]2wQ��)S��Vc�w�L3ݷ��{x�C��\���Ba������l��QH��P�ꍿsEQ����@)��iF��zwM����/]ë���V����|��Qio���2	��p��!}��Kc����쀮՝�1��y�|t0��ﾄS�h^}[�$��2*$Pj�$�{N�ƞҒ�=��z^��*�4�. ��0�l���2�"�5�8��VYi��p7: �/�	�~f
�k�{޺k����`��@����)�#�k	�L����3S2��9��lD���m�%\���T�0�<��J�Ә�B�ԒX
�� <˪��i9X�a@�+�kuw�L��(���U�S0Y�k|i��6޼zh0Ҳ���)Z�"�Z�L��8'��(�� ��$��c���\�xszX�&O��k��N�T�K��"TfN�;c��|�8�X��	�F 4�Ś 5e4C/����v�n/�S�6mi��m�����q��F�6���e��Ǧ�� {x��R��)�@N����$w?�{K/�4D��x̔�Q0/�D�"s�M�:�2��Qr���H��"*�@f��Qz`�.�Ӧ�]�7�L\�]	��B��
.��w���+�!Ъti��FäF�;��$������L-Lņp��@����Zq)3�-�*QJ+Ęv[���X��wVY�AR�@�sg�~L���naX��A!K�$��*t�6�C4�v�����:y�d1�g��RLR��VZ���p�ZͶ�t�.J�}a���2�vf��K��IY��`�a�V��_�3�0��-�o�hp6'�d>��ީ�J����%1�I��7j�x+vy��v/*UGZ7z�Č�k�"ގ���M��_H�¾֗���s�j����9G;,����E-?�y]�����Arj�Q��pټ�qj�[��a�2�	��t�#�˺ZL!��g
�M8�H����y�����.+�T�Xm��$�4r���CwZ�x�)xsN3�R&Ί@�U)1N��3v���`N����nз9��Ҏt57]#�.����7G�YA��o����ǿ6�e����[X�zK�%�E	��9����:�'4X���������fD6t��b%��v�P�/Q�+䇈o�q��9��D����W^6��eW��G1��[M�PkUp�'��\jy��?ЍhӇ��&xl�i�o<�1�s�����;Z��Ņy�(ɿx��w�U�rڟ @����u����=�����������0��+8�l�L�&'��.�"���|�u$�Y85�P�
��C�8R�Z+��ϨP���@��j�|��5P��@%2��[��[e��c3���$�<!��~2�ʔ�����߸yy��ݢ�0������='���ע�m�$�Ɍ'�ȲB;�r��Z�Y<`Z���Y��Ps�w	 ��y�o�M�����g@�۔�xGK�=�X��Ob����DE�q�뚃�']�A2�5���@� v��\�Y���{�j¤�m�OE�sYC`:�jU�Qv�:/�qqN�q�0���a��#$�]�r,-v?xE�I>��QvLY6�3�~hU�kӕ����S�1Ld�������ږ��zI ,I�!�\�a�>�~�k=�� ��ę�v�?h�q���+L�f�W�C<"/�17�9g�[��y�펗�O5L�F�^L�kdh_�r`i�;�.c
�p���d ���A`�5�f�o�iG�z��Nb�v�'��B��z7ipp*��ao�X0���Dv�v>�(����R�x�'�T�B%M��~b�'*�e�'��*��#©�h��}��rN�B�=���:�(��m 
j;E �=���}}?��"Ǎ]�
w�9}]:��DP�$ʮ��8�py/\�&*KrQ>��Gq�V*,ZnL(�A��S�\���M��;^آya��K�㵔�����*1Q[O(�դbK_��$g-�����nF\��O�J��[�Wp��R2��2�FY���X�gv/ļ>h?��B�����Ux�ڑ��!�ϜP�-̶b���;���BPf,�w�u�����2
�Q M�����uJ�xW�<�h$.�*Cwa]�I���>�Z$�2��V��%��&��;��Ål?s#��N��1���Sb���(�)����ÿ�Kl�	��l�F:w�c�R��A(���x3Z����3�m>�2�	e=�)m�gDd�mAeJ��#ܿ�    ����ם��*�3�m�F�s��W�� 1�b��}J2۟��ZBx8���!��z��/�2$�F���b��A-���Dr������:' jX"�Z�Q5
y��& c9M��(�eg�-J������@��}�b��d�	���/9���	�YM��6�9*��% 䟃k�))�>	z+}�)��D@Y4/�95���T��/rϦ{0O��[)�+Ry�����x	-�RC�XMaD�fVe_���-�v�n�n�(Ȇ�'�$����y7������-<Da5qXC�y�;�X��<0u<)�OA�#�	%.��A;o^��T��	;
����>h��S�����������Z�]�a�q.[8��}/��/1�O@a�@�7{�
S9(�1PL��rl�&��m@8~��+����QZ\h���� 2��y�U��-�a��x�2�%.�����	��-�e`ځ9�0��EP���G����7�/�I�ta���ו�[s)���8y�֗l^} ~�ȧ��	YQi���+��h��t�����
}V"��*+��#�3�zp���p�4exk���ZQ��B\q��'ZR�wN@,���SE�\���������?�_�e�U���@�J5}�U0ZFV0����VF�\���Nh�� ~E������y�{�:_\nc�t�|�?4��ȥ��8��rdL�O��6�e��2��V�%E|lA՘R�B�S}l�e�i��S����ba�����}u],�=�&3rb��N����W�B�Tv�M�%#�0�Υ�Ái��W�KR�/w(%����y�J�B�S_��r��ջ紸�#k�?Q����~T^��m��w`-%�SX?��S�$�ӊ�"2�� P�H�b4���rUl�2M�Kz�1���;s�|���'Gy�s,˴�FRM.�]�xIV��O��JR�z��"�����_QΔ���B�|���)r����l�h�RV�+NT�Chj��Z��۞���X����3�*�	�\��1 ��C�i�fe���5]����*q�H�gQz�����@O�����WO6�Z����d���*�fo�j�|mE{1�����r�GQ8�:@��G����\���x�gqR'��7
�;��մgZ#� 0�$@�G��M�554M�L�e���҈M/"E�R���ZC]ceař|&X���xf��Q~����������c뀤sK5L+ǯ�̔dA�WǶÛ�p���7S�@M��:�N�i����Q����c��R��SM�`���tttX��4]Ch蓁^� �`���f4�b���cN��4ԉd}�0b#����3můhx+q/�P ��CQ%��EU6o<
�9@#�~��Gk�??�{J�uu  ����N,����f��(��r+���v
a�Ei5��5|�{�
���U���o������R��(���:\�nz��P�M�b��.Ԟ^v.f+��� [(d~E�� JV��xٺ��KX|P��ͦ�h���<�����S��G#e��S�a���M��^�h3{�ђ�� N9�0c�*�1
c��t�B��lİں2
�X�Tt�7kn�'}u#jΖO�H�<w�Q�R���g@��g$ƙQ��p q�-ú�Zj<w�|�A1��%T�h��G�h0���" �)̀��ԋ��S#��Z,����(��ZɄvR��9��s����tPx�����B���?áuÙ��9�G�`�H2�����]N�k��8�����S�X�x����)X�ʠa�&�{�<��2Ilg�C���P����1G�P�`&��1q�"�?�$��%ɥ%����*�e��(���*F���������JФ��(;I�=:�C����,%���hC|6W�[IE��%i�
��3�����z�~E�0��L���DO"N
{��-��'�Qf�CG�c�u��㓪;��?h�Z2�dàLd��S�G2�����b�a`Հ)�+��͂��^0�I����y���dU����Q����34��)U �#4/Lp�T8�l�++�K�	IZ�HΐOݼ�,J�	'��  �=B).�L>�o���b\��?�nſ����0yb��i�͙�\<j���]��`��[{���Lo*��+�a�.��$�� �5
���� "��2R��}N��˥Y,Oy6�����Y�^
�����<�$� �Ui��{���
��c5���^���o����/��P���OJv}�2�г(���i5ߟ��_gS�5�K�c+T�o�X���ߴ�'a����KT��ҁࢭv�f0����/��ooT� 
��`�R�E��+��x�k�bt7������"�ƻ���An2!�:V�+�\#9�`����'��J�cʭI��Z���;!@��g�%(j'v�G�����8N#v�U1k·አgb:
�HdK���m}S��K���Y�n�H)�Լ��.f
;�RI΀�*������4(PcZb�9gX᯸�x��mJ54%m��"�o��A�j"�7�+t�|E��~VX|p���aR,�2\Q;!4���%�-��0��x��A�Lԩ9���7��=�o~�$bs3�T�-��Ł��a�4�p��D��I��'���t��M�Y����B�c0�,MJ�����a�� XHe�=�+=�3�+��'�M�fN����K���ny�m=|��>��8y�5CjF�p�Q��H���пh��,z#��γr���,:�J�ߕ���"L�=��)��W'Ju;΋JM�]�	]s`ro.�8bWi	��Dm�=Umv�ƒ#��`.Y:iQ�1���	���q�����%�8�	��uH�~�ۘ�1*�-v�fq�@�����b �"�(�����W�1�����aO�,��'��(�y3���?�/cY�F)� �#JB�Aa�~,#~���Of���R��	��S/&�ID'��;p ,a,M��]�٨V-����0�h�k' dO/�@�'<[@v�F�sH���dX�T8"��,�Ї^>�/��&���?tRxf(p����0Mlo�k�ZDMZ(��Lh��iF�0��k�%���ڗG��@������������󱔙�ݧ��hm�����	2r�RLR|�E}�$���!�r0.�!Q$/�HD�B>p�5��j����6�o��}���������x�D������H�[3�QI	\%e�ٟz��5ߩ�9��MN��'`D�\Р.�J`;)r�؂|b�T��:��B�)L��7V�����^\}��~��q�,�j�y�R���<L\��8�3`L��aļ,�"p��wB`�g�����t-�X"�I�v	���_̭��cE�W\D���<���W�2�;B�_sC���Sa��ecg��N��r}ܸC� g��/���S���β8_:����0]� ߑ�������ǁkw#P���A�_PԬ�����=]��Z��w�������5�&'����OW��`m���K�x�ms��.}2�.��'��5�~�6���D�H2AY��f>HQK�(�T�O�R���6�X�z�8Dt�$PX�o{?�o�8>��5�t��>�{�9,�&	h�[�%�lr� 3��Jv�<*Ȝ���'#�|0*)0xMG�r�)�I�C5�_�Z�G���{H%*���OB2!p�1}��ß�C�c��.<�$�1��q�3�i�5����q:�T�*1h���Xl���o�%P
3t�Eྒ����*���8�18��� %�w�����&4��Ƨy]u���������j��l��)Gd�~E�4`Z"{\lafҝY��!U��������7&��x-s�٧�=A�$�9'�	��C�R���+c���[mj��`��n��Ш8�}���S�RE.I���4uq���J�"_N+��Қ,֪L(�ROi�L/��hv[b����;�/���G^a0JT<��`�{彮Z����=Ρ*s���bx��j�%�����=��
7��    hpgǅ�q7)����*�k�Կ��qY">e9w
�8�i�;����>y�o�8�s�F�d�mcC��)J��0�k�G��馿@A��1�߹���)�W�9%��oq��vWJ%��/X�4aZpY�*G:�&Q�13��'af������r7��5،�)ct�M��0���F?vs�����fעr��z@[�B-
�V����^
P�Z��d�8U St3g�82g�x�\�"�ˍ��>���y���X�:\�E���`����w=.�{jTE'F��hO;����ƙ֦/��P����Șo5�{:��S&�p#O�?UQ?������3��Hǎw16�w_�����ZA�V����0u�f�F,kQ��p�Hӷ�0?n���������F���h&�[�0�ѕ�5�} ���'tu��EP�d�)����B j�J�|"����Na\���c7�Q�d��_A����+5���)���x͝KJ��عU%�9^Q��e^�"��<�t����o:���݃;���
s�_��f\�v�w�7Se���x&_|���@w�zԷ�:�<٦��^Bg�ê|�Bv����Goe6��n+��}.�]Ў�XX�V����y�����rh{�/�"�o��=�q	�q�q1T2P!���[,J���q����-ʞ|�X�?*h[�G;mnM�'@]p�j�`z|> ���?09�XY�/�E���	�K��,5�PS��8�_p�tC͑�1�� k��T��=����~ܭ�R�/�rBV��ŀ*>�$Un���e�F���T��A�Q�?�s�{x���m@♑tN��H��010L����{]ҏ�>E��G��޸��f5}Ml�gZT)>��Nh��_�C_�Y~c3o�v�]N��'��+j4�.��	�><V]�%qÌ%]��(}����6��=z�N�CE	+맅;�n0%�jY��(�۾����ς��+����i���UP��
#/zY�I7���Bn�l�l
	Ts)�&���J���T�vUN�������(9�p�;��������w�H�	+���w�$aam�ċ���gؿcQ�2���X5������	��re�e�5����xIT�z��&B0;�Ǿr���"k��Yʕp����nC��/ֽ0�:j��At�fSE�ObJ6��WuM5*���L�}��ҹ.�ׅ��'� ���Ώ��Z4jBĪ+�9��)k�9mX�juT�kJ�Vw﵌�"JuQ��~1��\���6-j���p<�;�OBk�-��A����-���o������k|��C]�*�7��*���
s��-�0��!�B��{�I���F�Z��~��YY����v^�3��8S/,о�:4�,������g��[���U$Tv�N!�*��z��+�V���.�#����b�<IY?����z��5s�%��J���N5sf ���+�IrB/Do��+���/^D���`��)|LY*ݗ�*.:-�[y�ڇ�z�#��b�����A�iH�{L�@
�?f�2/n 0m��!��|���K(l���@Ң}%�Z�}��Z��>H�W����ZfUA��EP�����C()]���ik��[���1�tn����O�^8��3��1���� 
��Z���� GS�ŗ�	66�X4a�7��-��1���G�98�8|�V��?=������ �;Vu�����U�Q��r_�����YT�a���<
��S&V����hY�E�|\��h�wB�,)�QG�i�����Ņ�ZM#�����8D�6�F.�I弎b��ZF
��o�o�U�����,�q��o2	��Kk�1{D��ZVN��SO����NN�����ޙNW�"L��B�U�v�z�LUD�H|}�a�n�qo�د�|�;��W��C[�
�������12�V�ZVg�$�>U���%[�VT^8���P�cj!���w����©���7���HR)�4F�������l�[���՞!���`A�Eu6��8��	dr�9�H^A	�M]�ihZ�B4F��q�7�Ó)4����$I�F�$W�����h�0��Xd�NN1*��%��63*&�cp��yߒEyoEc��J�������������R~»�i�F�/-ź{@�n��X ��Q�N�]-^y���^*#�ɝ�%{^����ǿ��o-�X�p�<wD���K��������5����6$v���TX)K̐�bs�Z�ݐLǩ4����㗼��ߚǭ�����T�:��cN�	�,��tc��xyIE�z���p�QU<*:M�^\�� �)-x����Oz�����1��b����Z�2k鿱�q�i���i)�q���Sӕ�k��U��,|K����X��8SK�h��������P����"��SX���<��Y����Hf��y��ɣ�E����0�����M��q6�<�q���z+U�1��p,�� &��u�	��G��%,�^��	F"�Q��wa���V(���8�
ĄJ�A<eK�CUU�Q������4?N��4�\�
.#��&���xv�s&�O���p�ʞ���L���۬�HB8�<x��w�|�}/Kc?R��`=``S���<�^k`B���Q�V�C�LX`��"_��VL\x�)g�[��qٞ@8���%h�-$�B-F	*��/��8%6~��J��Y�hn�}�r�h�;�;Zt�Ó�-��cYn���(^9D��c1��,�3�Y��T�f���/8�֤��=�u��Hee�/�yS��XpI�Mq+ ,��3�a��4��Sr�������JFS,�#xe�P
 e�=��`U��)Ƌ0y��$�yC�i��m�C����v��k�!ke^)|=gEՄ��콴t��}:�����a��{\��g�j|�t@�T➔��$5wr%�)0�k�]̒ W�}�<{�Z�E�
[����ێ�D�#����¢ΐm�#̻<S҄I��W^�@��m�D�������w曜T
sB���D�4�'@^��]R�J'Ya6���jac*�x�Y�E
1�?�~�
�\���Z1	�Jm�%pnk)an�N��i�u{s{�.jB-!�8s=ʁ�{��Zvv`�;���F%-��
FH6|��K՜�
$0�-�R����|,K)n�߸V��ּ�9�\L�[�hs��:�e����z�7e������c�����_���$ҙ��<�"FU?�I���W�qrH�EiK@��I�q_�~Wu�{R ����-�Y��6��g�
�݉:?A�I2h������>�"�˞�I�F���B�˽h�}���o�����D��kL�t�6��N���pV �8�ېz�P
�]�֝�����%զ�W-�1��V)�ߪ��D䡓�Q�z��G �,�X��U���`s����`�b�@S1�S�@?e����C[6��U�#)����_ZZ������cڏ[CE����>0w-���/P�795�Fr�N�c��w��L��^M�|��nf�3g`i`4�#��]]�����p�]
`�{�K��n4��"�˨��|�Վ��
�@�!��G��T�랥,����ɞ6�����@`5�(�
 r���;]L���K9WK�W�JN�EW�g�ۄ!Z��X��1Ɇn�,l�����_��=\^�LYhE���m���e:�g������K����}�J@!���T�?�$\��J¹�v������KT�ЮT��K8���b/k�UGRw��D#��&4���!h��r :m�����[ӆ�Ncֲ2�p�I

�*�TT�B�/՘7EV�5P�9"<������o׽��e�PHȀ�@m�N����C�(�+����V�H�sQ֮ٓo����<%�h����uq��c�8+��}���ȫ������R����1�I����(���u^�ء
��GdnZĩX�h�VT�ZT��3�ŝ����������G�?���@u����]i1ϒ����)��3��ݿH�    `�_��d���=��^^X���ڛe�#>s�T�� �b��9�zP' ��h�=s�[MoޢCLB�W��-��I�CL���+3[�}����E�6.�o�c��4Kn���ۮ�0-&�i3^��ż�t��j��Z2�˵�Ҿ,����͋a�w��q��Â�>5��qƁ�v.Y>"��z'��R�Ơp�'Y�[��K�}�"�3���:ث�d�ͼ�<`�0�k9I� c6_���� ���@�����>��y��m31H�9�g'�r0��	L#�R��1(�:��lt(%�J�5G�C�f�}:��WJ�-T*hC$R�Y�<��[F^��D�u�%4?�ƪIuA3��@�s{�P�Ixd2�HQk+����L4�O9�17��xYl� e���i+V&��
�q^��_��U�̙֔�wk�F;4��
���9��~�*k��J@[���
|q,@��(���Z�A{�S���1��`&` �M~e�w��nX�K3ݴ5z̋��-�{Te�4^�w�,\! �yF3��'���B�4Jj����ظ�Z���]�P��A��8���t�V2��-6�:�[t���x�T���(RB�?4��8m<��Y$/sL���Z����Y�-DWKM���tz�5X�5$���Jn���qX����+�T=��#��K*�/¡ϝWCu������8��BbɆ�*�b�@s0fU���?��W��0�x��'n�+0�;�A���ךkV��*�;X|�T�PCR�N!�Y+���D	�; �IA̿{��7�D���X2V�����DC�$��
o��Ov*��1G큄�ݔ�h���i�Y�p�q��&^���x=�yH�؄���=��熚�X�.�[�
f��J��LͭS��؄� ���Q~t���y�݃ ���D�/������w.V���$��kK��۱m�!�cF�n�:i�z��qY�a��c�Wܒq �J�_�8%lD.U��TTu�a����bf2^0��k�n�*�b��L���$uA-ʜ�����L�Yv+�hG���m^���m��oW-D�i�b�f�ev{���mr���>�<V� ��
qNX��76���j>`'����tՋE
���G��t�M�]+�dzj��Q*Or�F�bǰ�P�=�"z�~����Yt:k�q��\�'����'�����A�� ӀsG����|�y��v)��V7�*wD�϶>2Wކ���Y�l������W���Mn0�M���ޢ�!�lTz�jU<����=���b	���a�3-nP�C7[v�$8gM�l/3k�Mş��	N�u����Γ��O-�:�kWY)�Ђ=c���l\�O�[4�s�d\{�&���"	����Od�>�J�)2H�T�l��ao����됬���Zд���{���zwww������*�)�(PA�}�t����A���c���F�������^��9vmn�R0h�ȷ��	�j��G�h�?�W�RI<��ӹ�������󭅍������0���Z)���V��S��k[L\;�C�p.p�}�[u�z�=���F�AS�Է5�%��mLsQw���el��f\]W���k�`qs�*Fo|��:�N�XpOh�	�g_���͔e�D��}�is���i�l�F����Y�]pW��Ȓ��5]�;5+G�4�@*/j����k�
�{�3g���9�"\#�)��Y���2Ҍ�\�覣1���E�o `_A� 3���k�I��ɸ� ����|% �qu3��a�X��Ѝ[�Q�%1E�$Eqq��FxQ�=�H�j��������X3�mZQ��r��ɸ����,�k)XK�Xe�p�δ)R&ٔ:����}��?�_NN4}����M'�8�T�$�H`����C�s���Ԛ�mh?�`�X�}$P����_eI���J��,tHpw��:�Ј�-�ẗ$���^aBwj�����&��h��TF�

��b�PzC�
0Uv-)v!{7�O�Q@���s��
�1�_���x2r&��}pT��5�,@�=�Ug�H�l�(���	��~���v5�.��,	��~H�/�	ac}��/2y�R�pr�lh�������cUV5X�X<J+p��Ԋ�0�1��j�A�W�ʯJ��"7ZŚ$S ��̕��F�8�|gJW�UDD������W�H�ͪș����6Q�!��7%�����2Fqŏ3��z%�X������l�0��RT�-�*H��n_�j��=ڦ��tW¡�4�����h��}1��ˀ����4t��1�M����ӻ�߭���}X���t��C0���tz��L�5�yP�1�n�Q6��tj2͌J��/+�<p3�4�ֽeK�]�!�x�JNlCe
Ns�,f���ݪ���$U�������-����޵�FF�\�x�k�"�=2��v�eB|�r�Ț�1@��q�8GoBV��V�j�n>(7��eL�������A7T�gMS�9x�(��bL���Ɉ;�C8k������Q��N˓������p�P��%�Cq�oR�2��ڲA�y�^`� �K.�y ��ܽ�W.ZZ]{O�P]|�U�5L��+"E�7�.�ӯҐ^6���� ��/O�&���B"�2ƈ�_ś�B��vJ�����C��ؿ�aT��c�@����k��v�����!�,��;~�g�-0�Ϛ��I։I!���rY��_7�n�$Jw��]������lnl�����D�]��R�w��u���p���mj��w���������5>;A��6ѽ��}6��-�x��`ŵ��L���R<�[:��j-?Z#���o*���,��߽ы3��������K�|�ej�Y��_R!K1++qڱ���:�*� -=��L����f(����Es�_Ƈy�6J��Ѹ�)��dp�X.��_���a�����6���/��|����ٽO#~��w�%���qv��=�9U�0Ŏ�g�W*��� `'��FG�ʪ��s��];_/I1D�\���;��_:_�L""�t��S �\'���P	��?0�zo��f�D����c����'��]�/�[�{��w�%G�����f�h2�6���!V�U4QX�_��� �ƚ�E�ɈS� ԫ�_h#�8c����8mkBKv��C���wsKS�k���Η(���w�nLā��mC�LF0�\�A�]ČR��s��mF��(zQq�j�s'e��߆����4��D׻�"��{ި>u�1���[�`=1Z����a?yuߊ<����)����4���o{�~�rZ/����Ȭ�0]o�dX����,p�� J#<94���-J���DҘP�b`���f���ISl4){Ǫ����� VO�v,�/��U)�E�e���E",A��?�k��b����gx�.sI�X�a	�%��#}	��y��=y{}���wČ�f.�?�~zU��9̬�P�gEUt��au����腂s��]O�չM��y��`���x��d������0�=���5���e��ؗ��{������O�T�	?%�80���3P�^<�sˎ^<�񤬟������>�����J�4sZ��IP'jN�C�$h�_��v���B&�3D��o#�W1~s	��'o��Ls��sX>w�b)n��#�^��� �N��aL��i�M���p�{�<��(�j$��Ǻ�����e�d�Pz��bl����Ma���3>F��p��|��yځ6�&/�t�����ޖ�x�w(�&�,m-�ࡷ;W`�M5�Æ�����X�M�j\&N�آ!ًPPo�v�ax��ȕƌ#�^��a/�L4M+�Խrxd�-��6(��l�L����b��� ��Yk�j��N�e̹kE`����4��3{&@���$��b �(ڃ�[�I�E�����@ZzD|�Ѵn����j0������ܞE7����[�BϷ�wՑ\�x�P������=No�O�������l���u֧	�:&���p8|���Uv�}b��O )  ��{��'6��Q�Q�����xo���E�(�y�S&C���FQ��� �e ����<AȪ�}C�R=��Y=�e$��,��a�b�\��_I2@��ݐU�$���G ~"��^��t��w� ꘴�~�����J�����ޟip�F~��H~E���d5᱒T�^��W�+�̖s�o����'��r�HN�E	�`mb0#�#�m��,&�\�8�]�k.L`elhA	����c��9�S�x;U�OM�!�m�&�SeS�8�4Ğ�9Oт�UU^.� � �#!��T��R�*Yd_�c�.AY���M��	�1��d�����wvW�g���xɝ��Svw*d��e�?S��G��N�T�(�0=�\���o��iM�g��� ��ӪT�x����F&��2��%�$�Jq�@YEN`���^�w��l�<�{��b�N�4J��{\�Є�肘��@��1]��	7";�A� ������N��[��	K�m�Im%(s�<��p���G�� V^50�!2�ݩ� �e��ҹ�u��h��H]h�3\�����zB_����H���	����e��߅IM/I�D�z���	��J?箬��i%�`�0�� Nx\�FJ~~��<9A���OrC�rWL��BO�����r��e/Pd~��2���sڜ��5�w�bJ�+w�@�QNs����y i��*0H9Z��;� �ҎJ�ɜ�M��*�g&��@�4�}r8{���|�`���t��To��!1S�-���D��v�o-%|���xGȟ�Qr2�U��͋��U�k�����q�0�g���.���t�HDd#D�9�.�]�`��!Z{��<U�Sb��"Jlկ������`�SN:/gzg��Ќ������q��p��)��}�s��C	yG�����@4�fQ�5Έ����H6�uX�v8vg���^�dC~D�����(W�E��J�iʪ)��Sq�b��}���m�L���zHd�FC+����F*��*S���4�P\�D�k�k��5c?\����ul0�=.� y� 	ǧS�e�Ob	�2��b�_��(�Uc�D�H�n9U�Ǩ	 NGU@N��/;̩��?-<c��3ְ�OoE�S 0��
�Km�j���𕩨���c��dΒ~��c��ġ�4�&�j��GH��'��Du�����O-����
�������=���z��K��]�ū�0M��ǜ�i��u���+�"7OU�W^$F�R���薍O#�ú�Tu]�������u�-jݐ�-����M�V ��L��|j@��?jp��Zl������)���v�O�]_���M4[ѩ�L���q�+�;�+|\���}��=�F� CX�����t��'��i�V�;^����K���Y�Y#T��|��+�mg����U�1N_|���)�5�{�9R�/��(l<"�i����9�_U��������F�x��a-&��3��m���e�� U愷b# VN�3�ʒ�p;� ��5�i68g�}~&2�������������X��-      @   ?   x�3�4202�54�52�446�30 ��D���I���1�i1�i�� i������� `�?      A   N   x�IL�.��K��t�M,J�I��-M�T(�H��t
r�sv��.�KWH+�rq�rN,�O�,J��qJ{9r��qqq O��      B   #   x�3�4�4200�54�54�42�<�=... aMj      D   <   x�3�IL�.��K��t�M,J�I�)��M-.)J��4202�50�542�� L�=... o      E   D   x��-M�T(�H���N,J,9��+$15�<?/%�˻4/]!���9� ?5�(��+�T�+����+F��� �7k      F   T   x�IL�.��K��)��M-.)J��442�30��-M�T(�H��N�K�����S3�Qt����K�B�c ������ "�      G   X   x��I��K,J��tL����,.)JL�/�442�
I-.I�t��K�KN�,��p$�e&gs:'�$�����&'#+
�p��qqq � �     