Readability Analysis
In this assignment, you will train a binary logistic regression model to predict whether a given Java method is more or less readable. Software readability is a property that influences how easily some piece of code can be read and understood. Since readability can affect maintainability and the general software quality, developers should aim to write code that is easy to read. Thus, deploying a model that automatically deems whether some piece of code is easy to read or not may be a helpful tool for programmers to maintain an easy-to-read code base that is less prone to faulty program behaviour.

Task Description
The aim of this task is to predict whether a Java method given as a .jsnp Java snippet file is readable or not. To this end, we train and evaluate a binary logistic regression classifier by employing a two-step process. First, in the preprocess stage, we establish a training and evaluation dataset for our classifier in the form of a CSV file. Each row of this CSV file maps a code snippet to a set of three code readability features and to the ground truth value that deems whether the code is readable or not as determined by human raters. Using the training dataset generated in phase one, we continue in the second step with training and evaluating our binary classifier on the generated dataset using the WEKA machine learning library.

You can invoke the preprocessing routine by executing the preprocess subcommand using the following command line parameter:

-s, --source for specifying the path to the directory hosting .jsnp files.
-g, --ground-truth for specifying the ground truth .csv file hosting human ratings of the specified code snippets.
-t, --target for specifying the target .csv file to save the preprocessed data in.
A parameter list of FeatureMetrics selected from [LINES, TOKEN_ENTROPY, H_VOLUME].
We will provide you with a dataset of 200 .jsnp files and the corresponding CSV file that hosts the ground-truth data in the form of human readability ratings. Your task will be to implement the three readability features LINES, TOKEN_ENTROPY, H_VOLUME and apply them to the code snippets in order to generate the training dataset. The first feature, called LINES, computes the number of lines in the given Java snippet. Since we are interested in determining the readability of some piece of code as experienced by a human, the feature also counts non-executable lines such as comments and empty lines.

TOKEN_ENTROPY measures the Shannon Entropy of the token stream extracted from a given code snippet
s
s. This token stream may be extracted from the code snippet using the getTokenRange() method of the JavaParser library after parsing the snippet string using the provided parseJavaSnippet(String codeSnippet) method in the abstract FeatureMetric class. The entropy is defined as
H
(
s
)
=
−
∑
i
=
1
n
p
(
x
i
)
∗
l
o
g
2
(
p
(
x
i
)
)
H(s)=−∑
i=1
n
​
p(x
i
​
)∗log
2
​
(p(x
i
​
)), with
p
(
x
i
)
p(x
i
​
) representing the probability of encountering token
x
i
x
i
​
in snippet
s
s. In order to compute the probability
p
(
x
i
)
p(x
i
​
), we can use the formula
p
(
x
i
)
=
c
o
u
n
t
(
x
i
)
∑
j
=
1
n
c
o
u
n
t
(
x
j
)
p(x
i
​
)=
∑
j=1
n
​
count(x
j
​
)
count(x
i
​
)
​
by counting the number of occurrences
c
o
u
n
t
(
x
)
count(x) of each token
x
x in the provided code snippet.

Finally, the feature H_VOLUME computes the Halstead Volume of the given Java method. To this end, we first have to count the total number of operators
N
1
N
1
​
and operands
N
2
N
2
​
as well as the unique number of operators
n
1
n
1
​
and operands
n
2
n
2
​
in the provided code snippet. Next, we can then derive the program length
N
=
N
1
+
N
2
N=N
1
​
+N
2
​
and the program vocabulary
n
=
n
1
+
n
2
n=n
1
​
+n
2
​
by summarising the total and unique number of operators and operands, respectively. Based on the program length and the program vocabulary, we can then compute the Halstead Volume
V
(
s
)
V(s) of a given code snippet using the formula
V
(
s
)
=
N
∗
l
o
g
2
(
n
)
V(s)=N∗log
2
​
(n). In order to determine the total and unique number of operators and operands, you have to use the VoidVisitorAdapter of the JavaParser library to implement the OperatorVisitor and OperandVisitor classes. This adapter comes with numerous visitors, which you'll have to use in order to visit the parts of the code that constitute as operators and operands. Generally, operators correspond to special symbols that perform specific actions on operands. For instance, the statement x = y + z consists of a binary addition operation (+) and an assignment operation (=), which involve the operands x, y and z. We restrict the set of operators according to the given OperatorType enum, which includes assignment, binary, unary, conditional and type comparison operations. The set of operands is restricted to literals such as boolean, string and null literals, as well as method parameters.

The second phase, which is responsible for training and evaluating a readability classifier on our preprocessed dataset, can be invoked using the classify subcommand. This subcommand expects the command line input -d, --data that defines the path to the training dataset we generated in the preprocessing step. Your task will be to implement the training and evaluation routine for a logistic regression model using the WEKA library. To this end, you will have to implement the two methods loadDataset() and trainAndEvaluate(Instances dataset). The former method is responsible for mapping our CSV training dataset into an Instances object, which is then consumed by the latter method in order to train and evaluate the readability classifier. For the training and evaluation process, please instruct the WEKA library to apply 10-fold cross-validation using a starting seed of 1.

Evaluation Criteria
Unit Tests
Correct implementation of NumberLinesFeature class No results
Correct implementation of TokenEntropyFeature class No results
Correct implementation of HalsteadVolumeFeature class No results
Functional Tests
Correct implementation of preprocessing phase No results
To pass this test, you have to implement the collectCSVBody() method of SubcommandPreprocess such that each row corresponds to one code snippet. To this end, please follow the CSV column format File,NumberLines,TokenEntropy,HalsteadVolume,Truth, with File representing the file name of the code snippet, NumberLines,TokenEntropy,HalsteadVolume the respective feature values rounded two decimal places and Truth representing the binary value Y or N based on whether the average rater score of a given snippet is greater or equal (=Y) than the TRUTH_THRESHOLD. Make sure that the entries of the resulting CSV file are ordered according to the filename (1.jsnp -> 2.jsnp -> 3.jsnp -> …).

Correct implementation of classification phase No results
To pass this test, you have to implement the loadDataset() and trainAndEvaluate(Instances dataset) methods of the SubcommandClassify class. Please make sure that you use the WEKA machine library together with a logistic classifier that is evaluated using 10-fold cross-validation with a starting seed value of 1.

Working preprocess-classify pipeline No results
This test serves as an integration test that first generates a training dataset using the preprocess subcommand and the trains the logistic regression model on the generated dataset using the classify subcommand.

Coverage Values
Line Coverage No results
Branch Coverage No results
Mutation Score No results